package com.Converso.Moneda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConversorMoneda {
    private static final String API_URL = "https://api.coingecko.com/api/v3/exchange_rates";

    public static Map<String, Double> obtenerTasasCoinGecko() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error HTTP: " + response.statusCode());
        }

        Gson gson = new Gson();
        RespuestaCoinGecko data = gson.fromJson(response.body(), RespuestaCoinGecko.class);

        Map<String, Double> tasas = new LinkedHashMap<>();
        for (Map.Entry<String, RespuestaCoinGecko.Moneda> entry : data.getRates().entrySet()) {
            tasas.put(entry.getKey().toUpperCase(), entry.getValue().getValue());
        }
        return tasas;
    }
}
