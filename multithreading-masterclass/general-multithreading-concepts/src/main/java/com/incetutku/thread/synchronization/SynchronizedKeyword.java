package com.incetutku.thread.synchronization;

import com.incetutku.thread.management.LocalVariablesAndRaceConditions;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedKeyword {
    private static int globalCounter = 0;
    private static final Object obj = new Object();

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        ThreadGroup group = new ThreadGroup("Group");

        for (int i = 1; i <= 1000; i++) {
            Thread thread = new Thread(group, new MyThread());
            thread.start();
            threads.add(thread);
        }

        group.interrupt();

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.printf("Total = %s ", globalCounter);
    }


    static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(99999);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
            synchronized (obj) {
                globalCounter++;
            }
        }
    }
}
