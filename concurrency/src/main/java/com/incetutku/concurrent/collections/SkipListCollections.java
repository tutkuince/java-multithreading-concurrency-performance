package com.incetutku.concurrent.collections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class SkipListCollections {
    public static void main(String[] args) {
        Set<String> countries = new ConcurrentSkipListSet<>(); // Concurrent TreeSet
        countries.add("Türkiye");
        countries.add("Germany");
        countries.add("Canada");
        countries.add("Australia");

        // natural order for Strings is alphabetic
        for (String country :countries) {
            System.out.println(country);
        }

        Map<String, Integer> map = new ConcurrentSkipListMap<>();
        map.put("Jack", 12);
        map.put("Zack", 15);
        map.put("Anna", 22);

        // ordered by keys
        for (String key :map.keySet()) {
            System.out.printf("%s -> %s \n", key, map.get(key));
        }
    }
}
