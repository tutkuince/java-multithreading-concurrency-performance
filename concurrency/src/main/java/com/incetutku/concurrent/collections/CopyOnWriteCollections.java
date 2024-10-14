package com.incetutku.concurrent.collections;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteCollections {
    public static void main(String[] args) {
        List<String> names = new CopyOnWriteArrayList<>();
        names.add("Tutku");
        names.add("Ann");
        names.add("Brian");

        // API: The snapshot style iterator method uses a reference to the state of the array at the point that the iterator was created.
        // This array never changes during the lifetime of the iterator, so interference is impossible and the iterator is guaranteed not to throw Exception

        names.forEach(name -> {
            System.out.println(name);
            names.add(name);
        });
        System.out.println(names);
        System.out.println("------------");

        Set<String> uniqueNames = new CopyOnWriteArraySet<>();
        uniqueNames.add("Tutku");
        uniqueNames.add("Ann");
        uniqueNames.add("Brian");
        System.out.println(uniqueNames);
        System.out.printf("Size is %s", uniqueNames.size());
    }
}
