package com.incetutku.thread.synchronization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CrushingMatrixWithBarriers {
    private final static int[][] array = {
            {1, 2, 3, 1},
            {2, 1, 2, 1},
            {1, 2, 1, 3},
            {1, 1, 1, 2}
    };

    private final static int[][] output = {
            {1, 2, 3, 1},
            {9, 8, 9, 8},
            {35, 36, 35, 37},
            {144, 144, 144, 145}
    };

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("The barrier was released!"));

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new WorkerThread(i));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("The final array: " + Arrays.deepToString(array));
    }

    static class WorkerThread implements Runnable {
        private final int columnId;

        public WorkerThread(int columnId) {
            this.columnId = columnId;
        }

        @Override
        public void run() {
            for (int i = 1; i < 4; i++) {
                int S = 0;

                for (int j = 0; j < 4; j++) {
                    S = S + array[i - 1][j];
                }

                array[i][columnId] = array[i][columnId] + S;
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
