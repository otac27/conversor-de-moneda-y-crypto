# 💱 Conversor de Moneda y Criptomonedas - Java + CoinGecko API

Este proyecto fue desarrollado como parte del programa **Oracle Next Education (ONE)** junto a **Alura Latam**. Se trata de una aplicación de consola escrita en **Java**, capaz de realizar conversiones entre monedas tradicionales y criptomonedas, utilizando datos actualizados en tiempo real de la **CoinGecko API**.

---

## 🚀 Tecnologías utilizadas

- Java 11 o superior
- CoinGecko API (fuente de datos de conversión)
- Gson (para procesar JSON)
- `HttpClient` nativo de Java
- IntelliJ IDEA (entorno de desarrollo sugerido)

---

## 🧩 Funcionalidades

- Menú dinámico con múltiples pares de monedas
- Conversión en tiempo real entre monedas y criptoactivos
- Validación de entradas del usuario
- Historial de conversiones con fecha y hora
- Exportación de historial a `historial_conversiones.txt`
- Código limpio y modular listo para expansión

---

## 💱 Monedas soportadas

Este conversor trabaja inicialmente con las siguientes monedas y criptomonedas:

- USD – Dólar estadounidense  
- EUR – Euro  
- ARS – Peso argentino  
- BOB – Boliviano  
- BRL – Real brasileño  
- CLP – Peso chileno  
- COP – Peso colombiano  
- BTC – Bitcoin  
- ETH – Ethereum

🛠 Puedes modificar fácilmente esta lista desde la constante `MONEDAS_FILTRADAS` ubicada en el archivo `Main.java`.

---

## 📦 Instalación y uso

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/conversor-de-moneda-y-crypto.git
