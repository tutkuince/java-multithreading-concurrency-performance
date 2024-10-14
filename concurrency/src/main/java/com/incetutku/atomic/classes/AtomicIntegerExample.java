package com.incetutku.atomic.classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// No duplicated numbers; guaranteed to get to 10.
// However, order is still not guaranteed
public class AtomicIntegerExample {
    // private static int count = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            executorService.submit(() -> System.out.print(atomicCounter.incrementAndGet() + " ") );
        }
        executorService.shutdown();
    }
}
