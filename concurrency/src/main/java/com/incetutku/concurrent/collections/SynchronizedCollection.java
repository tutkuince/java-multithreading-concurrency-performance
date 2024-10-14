package com.incetutku.concurrent.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedCollection {
    public static void main(String[] args) {
        Map<String, String> capitalCities = new HashMap<>();
        capitalCities.put("Oslo", "Norway");
        capitalCities.put("Copenhagen", "Denmark");

        Map<String, String> synCapitalCities = Collections.synchronizedMap(capitalCities);
        synCapitalCities.keySet().forEach(capitalCity -> {
            System.out.printf("%s is the capital of %s\n", capitalCity, synCapitalCities.get(capitalCity));
            // synCapitalCities.remove(capitalCity); ConcurrentModificationException
        });
    }

}
