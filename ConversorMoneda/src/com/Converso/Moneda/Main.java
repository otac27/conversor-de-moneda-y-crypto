package com.Converso.Moneda;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final List<String> MONEDAS_FILTRADAS = List.of("USD", "ARS", "BOB", "BRL", "CLP", "COP", "BTC", "ETH", "EUR");
    private static final List<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("\n***************************************");
        System.out.println("*  Sea bienvenido/a al Conversor de Moneda =]  *");
        System.out.println("***************************************");

        while (continuar) {
            try {
                Map<String, Double> tasas = filtrarMonedas(ConversorMoneda.obtenerTasasCoinGecko());
                Map<Integer, String[]> opciones = construirOpciones(tasas);

                imprimirMenu(opciones);

                System.out.print("Seleccione una opción: ");
                int seleccion = Integer.parseInt(scanner.nextLine());

                if (seleccion == opciones.size() + 1) {
                    mostrarHistorial();
                    System.out.println("👋 ¡Gracias por usar el conversor!");
                    break;
                }

                if (!opciones.containsKey(seleccion)) {
                    System.out.println("⚠ Opción inválida.\n");
                    continue;
                }

                String origen = opciones.get(seleccion)[0];
                String destino = opciones.get(seleccion)[1];
                double monto = pedirMonto(scanner, origen);
                double resultado = convertirMoneda(monto, origen, destino, tasas);

                mostrarResultado(monto, origen, resultado, destino);
                guardarHistorial(monto, origen, resultado, destino);

            } catch (NumberFormatException e) {
                System.out.println("⚠ Entrada inválida. Debes ingresar un número.");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static Map<String, Double> filtrarMonedas(Map<String, Double> tasas) {
        Map<String, Double> filtradas = new LinkedHashMap<>();
        for (String codigo : MONEDAS_FILTRADAS) {
            if (tasas.containsKey(codigo)) {
                filtradas.put(codigo, tasas.get(codigo));
            }
        }
        return filtradas;
    }

    private static Map<Integer, String[]> construirOpciones(Map<String, Double> tasas) {
        Map<Integer, String[]> opciones = new LinkedHashMap<>();
        int contador = 1;
        for (String origen : tasas.keySet()) {
            for (String destino : tasas.keySet()) {
                if (!origen.equals(destino)) {
                    opciones.put(contador++, new String[]{origen, destino});
                }
            }
        }
        return opciones;
    }

    private static void imprimirMenu(Map<Integer, String[]> opciones) {
        System.out.println("\nOpciones disponibles:");
        for (Map.Entry<Integer, String[]> entrada : opciones.entrySet()) {
            int num = entrada.getKey();
            String[] par = entrada.getValue();
            System.out.printf("%2d) %s =>> %s%n", num, par[0], par[1]);
        }
        System.out.printf("%2d) Salir%n", opciones.size() + 1);
        System.out.println("***************************************");
    }

    private static double convertirMoneda(double monto, String origen, String destino, Map<String, Double> tasas) {
        double tasaOrigen = tasas.get(origen);
        double tasaDestino = tasas.get(destino);
        return monto / tasaOrigen * tasaDestino;
    }

    private static double pedirMonto(Scanner scanner, String moneda) {
        System.out.printf("Ingrese el monto en %s: ", moneda);
        return Double.parseDouble(scanner.nextLine());
    }

    private static void mostrarResultado(double monto, String origen, double resultado, String destino) {
        System.out.printf("💱 Resultado: %.4f %s = %.4f %s%n%n", monto, origen, resultado, destino);
    }

    private static void guardarHistorial(double monto, String origen, double resultado, String destino) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String registro = String.format("[%s] %.4f %s = %.4f %s", timestamp, monto, origen, resultado, destino);
        historial.add(registro);

        try (FileWriter fw = new FileWriter("historial_conversiones.txt", true)) {
            fw.write(registro + "\n");
        } catch (IOException e) {
            System.out.println("⚠ No se pudo guardar el historial en archivo.");
        }
    }

    private static void mostrarHistorial() {
        System.out.println("\n📜 Historial de conversiones:");
        if (historial.isEmpty()) {
            System.out.println(" - No se realizaron conversiones.");
        } else {
            historial.forEach(linea -> System.out.println(" - " + linea));
        }
        System.out.println("📁 También se guardó en 'historial_conversiones.txt'");
    }
}