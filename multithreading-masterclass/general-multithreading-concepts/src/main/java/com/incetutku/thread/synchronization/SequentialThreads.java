package com.incetutku.thread.synchronization;

public class SequentialThreads {
    private final Object lock = new Object();
    private boolean firstThreadTurn = true;

    public static void main(String[] args) {
        SequentialThreads app = new SequentialThreads();
        app.startThreads();
    }

    public void startThreads() {
        Thread thread1 = new Thread(new FirstThread(), "Thread-1");
        Thread thread2 = new Thread(new SecondThread(), "Thread-2");

        thread1.start();
        thread2.start();
    }

    class FirstThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (!firstThreadTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Thread interrupted");
                        }
                    }
                    System.out.println("Thread 1 is running and sending message to Thread 2");
                    firstThreadTurn = false;
                    lock.notify();
                }
            }
        }
    }

    class SecondThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (firstThreadTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Thread interrupted");
                        }
                    }
                    System.out.println("Thread 2 received message from Thread 1");
                    firstThreadTurn = true;
                    lock.notify();
                }
            }
        }
    }
}
