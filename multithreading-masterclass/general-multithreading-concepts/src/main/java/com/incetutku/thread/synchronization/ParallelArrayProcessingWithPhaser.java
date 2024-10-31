package com.incetutku.thread.synchronization;

import java.util.concurrent.Phaser;

public class ParallelArrayProcessingWithPhaser {
    private static int S = 0;
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

    private static Phaser phaser = new Phaser(1);

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            Thread thread = new Thread(new WorkerThread(i));
            thread.start();
        }

        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();

        System.out.printf("The sum is: %s", S);
    }

    static class WorkerThread implements Runnable {
        private final int threadIndex;

        public WorkerThread(int threadIndex) {
            this.threadIndex = threadIndex;
            phaser.register();
        }

        @Override
        public void run() {
            array[threadIndex] = array[threadIndex] * 2;
            phaser.arriveAndAwaitAdvance();

            if (threadIndex == 0) {
                for (int j : array) {
                    S = S + j;
                }
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }
    }
}
