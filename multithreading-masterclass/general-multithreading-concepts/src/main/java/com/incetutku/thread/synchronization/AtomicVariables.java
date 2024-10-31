package com.incetutku.thread.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.printf("The number is: %s", atomicInteger.get());
    }
}
