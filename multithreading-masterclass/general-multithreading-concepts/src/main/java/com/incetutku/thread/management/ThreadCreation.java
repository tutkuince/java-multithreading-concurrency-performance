package com.incetutku.thread.management;

public class ThreadCreation {

    public static void main(String[] args) throws InterruptedException {
        // Thread Creation
        // 1. Extend the Thread Class
        // 2. Use Runnable Interface

        /*MyThread myThread = new MyThread();
        myThread.start();*/

        System.out.printf("[1] Current thread: %s \n", Thread.currentThread().getName());

        Thread thread = new Thread(
                // Runnable Interface
                () -> {
                    System.out.printf("[2] Current thread: %s \n", Thread.currentThread().getName());
                }
        );
        thread.start();
        thread.join();

        System.out.printf("[3] Current thread: %s \n", Thread.currentThread().getName());
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            setName("MyThread");
            System.out.printf("Current thread: %s \n", Thread.currentThread().getName());
        }
    }
}
