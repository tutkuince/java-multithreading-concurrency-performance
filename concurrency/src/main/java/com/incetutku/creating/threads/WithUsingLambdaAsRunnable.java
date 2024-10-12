package com.incetutku.creating.threads;

public class WithUsingLambdaAsRunnable {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("run(): " + Thread.currentThread().getName()));
        thread.start();
        System.out.println("main(): " + Thread.currentThread().getName());
    }
}
