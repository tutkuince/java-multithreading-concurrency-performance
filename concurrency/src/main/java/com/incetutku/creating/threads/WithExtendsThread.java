package com.incetutku.creating.threads;

public class WithExtendsThread extends Thread {

    public static void main(String[] args) {
        new WithExtendsThread().start();
        System.out.println("main(): " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run(): " + getName());
    }
}
