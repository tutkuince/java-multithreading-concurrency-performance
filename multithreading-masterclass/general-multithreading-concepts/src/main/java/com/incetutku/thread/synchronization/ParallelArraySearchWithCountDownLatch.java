package com.incetutku.thread.synchronization;

import java.util.concurrent.CountDownLatch;

public class ParallelArraySearchWithCountDownLatch {
    private static int foundPosition = 0;
    private static final int NUMBER_OF_THREAD = 2;
    private static final int NUMBER_TO_SEARCH = 8;
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final CountDownLatch countDownLatch = new CountDownLatch(NUMBER_OF_THREAD);

    public static void main(String[] args) throws InterruptedException {
        int threadSlice = array.length / NUMBER_OF_THREAD;

        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            Thread thread = new Thread(new WorkerThread(i * threadSlice, (i + 1) * threadSlice));
            thread.start();
        }

        countDownLatch.await();

        System.out.printf("The number was found on position: %s", foundPosition);
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
                if (array[i] == NUMBER_TO_SEARCH) {
                    foundPosition = i;
                }
            }
            countDownLatch.countDown();
        }
    }
}
