package com.incetutku.thread.management;

public class CommonStaticThreadMethods {
    // Common Static Thread Methods
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.printf("Current thread: %s \n", thread.getName());

        Thread.sleep(3000);

        System.out.printf("Current thread: %s \n", thread.getName());
    }
}
