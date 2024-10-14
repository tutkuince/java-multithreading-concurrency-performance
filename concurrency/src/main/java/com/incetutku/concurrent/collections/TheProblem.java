package com.incetutku.concurrent.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TheProblem {
    public static void main(String[] args) {
        // Map<String, String> capitalCities = new HashMap<>();    // Exception in thread "main" java.util.ConcurrentModificationException
        Map<String, String> capitalCities = new ConcurrentHashMap<>();
        capitalCities.put("Oslo", "Norway");
        capitalCities.put("Copenhagen", "Denmark");
        capitalCities.put("Ankara", "Türkiye");

        capitalCities.keySet().forEach(capitalCity -> {
            System.out.printf("%s is the capital of %n", capitalCities.get(capitalCity));
            capitalCities.remove(capitalCity);
        });
    }
}
