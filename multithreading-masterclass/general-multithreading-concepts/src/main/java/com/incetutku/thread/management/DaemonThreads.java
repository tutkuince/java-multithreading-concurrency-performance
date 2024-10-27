package com.incetutku.thread.management;

public class DaemonThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyThread(10), "Thread-1");
        Thread thread2 = new Thread(new MyThread(3), "Thread-2");

        thread1.setDaemon(true);    // When thread 2 process is finished, main thread will finish the execution also.
                                    // Not need to wait thread1

        thread1.start();
        thread2.start();

        // thread1.join(); glitch -> it acts like not Daemon thread
    }


    static class MyThread implements Runnable {

        private final int numberOfSeconds;

        public MyThread(int numberOfSeconds) {
            this.numberOfSeconds = numberOfSeconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfSeconds; i++) {
                System.out.printf("Sleeping for 1s, thread: %s \n", Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
