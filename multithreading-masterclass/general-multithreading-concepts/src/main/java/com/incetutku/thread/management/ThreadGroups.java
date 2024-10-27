package com.incetutku.thread.management;

public class ThreadGroups {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("Group1");
        group.setMaxPriority(7);

        ThreadGroup parent = group.getParent();
        System.out.printf("Name: %s, Priority = %s \n", parent.getName(), parent.getMaxPriority());


        Thread thread1 = new Thread(group, new MyThread(), "Thread-1");
        Thread thread2 = new Thread(group, new MyThread(), "Thread-2");
        Thread thread3 = new Thread(group, new MyThread(), "Thread-3");

        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Sleeping for 3 seconds...");
        Thread.sleep(3000);

        group.interrupt();
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread currentThread = Thread.currentThread();
                    System.out.printf("Name: %s, Priority = %s \n", currentThread.getName(), currentThread.getPriority());
                }
            }
        }
    }
}
