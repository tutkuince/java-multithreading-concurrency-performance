package com.incetutku.thread.synchronization;

import java.util.concurrent.Exchanger;

public class ShiftDataBetweenThreadsWithExchanger {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread = new Thread(() -> {
            try {
                String receivedValue = exchanger.exchange("value1");
                System.out.printf("Received: %s in thread %s\n", receivedValue, Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        String receivedValue = exchanger.exchange("value2");
        System.out.printf("Received: %s in thread %s", receivedValue, Thread.currentThread().getName());
    }
}
