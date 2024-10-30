package com.incetutku.thread.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWrite locks and Spin Locks
 */
public class ReadWriteAndSpinLocks {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Thread writer = new Thread(new WriteThread());

        Thread reader1 = new Thread(new ReadThread());
        Thread reader2 = new Thread(new ReadThread());
        Thread reader3 = new Thread(new ReadThread());
        Thread reader4 = new Thread(new ReadThread());

        writer.start();
        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
    }

    static class WriteThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    writeData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void writeData() throws InterruptedException {
            Thread.sleep(3000);
            writeLock.lock();
            int value = (int) (Math.random() * 10);
            System.out.println("Producing data: " + value);

            list.add(value);

            writeLock.unlock();
        }
    }

    static class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    readData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void readData() throws InterruptedException {
            Thread.sleep(3000);

            
            while (true) {
                boolean acquired = readLock.tryLock();
                if (acquired) {
                    break;
                } else {
                    System.out.println("Waiting for read lock");
                }
            }

            System.out.println("List is: " + list);

            readLock.unlock();
        }
    }
}
