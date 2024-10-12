package com.incetutku.creating.threads;

public class WithImplementRunnable implements Runnable {

    public static void main(String[] args) {
        new Thread(new WithImplementRunnable()).start();
        System.out.println("main(): " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run(): " + Thread.currentThread().getName());
    }
}
