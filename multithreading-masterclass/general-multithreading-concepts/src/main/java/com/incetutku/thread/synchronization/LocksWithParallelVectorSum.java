package com.incetutku.thread.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LocksWithParallelVectorSum {

    private static int COUNTER = 0;
    private static int[] array = new int[10];
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            array[i] = 10;
        }

        int threadSlice = array.length / 2;

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new WorkerThread(i * threadSlice, (i + 1) * threadSlice));
            thread.start();
            threadList.add(thread);
        }

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("The sum is: " + COUNTER);
    }

    static class WorkerThread implements Runnable {
        private final int left;
        private final int right;

        public WorkerThread(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                lock.lock();
                COUNTER = COUNTER + array[i];
                lock.unlock();
            }
        }
    }
}
