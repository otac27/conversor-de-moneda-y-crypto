package com.Converso.Moneda;

import java.util.Map;

public class RespuestaCoinGecko {
    private Map<String, Moneda> rates;

    public Map<String, Moneda> getRates() {
        return rates;
    }

    public static class Moneda {
        private String name;
        private String unit;
        private double value;

        public String getName() {
            return name;
        }

        public String getUnit() {
            return unit;
        }

        public double getValue() {
            return value;
        }
    }
}
