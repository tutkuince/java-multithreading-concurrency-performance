package com.incetutku.thread.synchronization;

public class TheVolatileKeyword {
    private static volatile int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int localCounter = counter;

            while (localCounter < 10) {
                if (localCounter != counter) {
                    System.out.println("[Thread-1] Local counter is changed!");
                    localCounter = counter;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int localCounter = counter;

            while (localCounter < 10) {
                System.out.println("[Thread-2] Incremented counter to " + (localCounter + 1));
                counter = ++localCounter;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
