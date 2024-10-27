package com.incetutku.thread.management;

public class ThreadPrioritiesAndState {
    // Thread Priority
    /*public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Thread thread1 = new Thread(
                () -> {
                    Thread currentThread = Thread.currentThread();
                    System.out.printf("%s priority = %s \n", currentThread.getName(), currentThread.getPriority());
                }
        );
        thread1.setName("Thread_1");
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread(
                () -> {
                    Thread currentThread = Thread.currentThread();
                    System.out.printf("%s priority = %s \n", currentThread.getName(), currentThread.getPriority());
                }
        );
        thread2.setName("Thread_2");
        thread2.setPriority(Thread.MIN_PRIORITY);


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    Thread currentThread = Thread.currentThread();
                    System.out.printf("[1] State: %s \n", currentThread.getState());
                }
        );

        System.out.printf("[2] State: %s \n", thread.getState());

        thread.start();

        System.out.printf("[3] State: %s \n", thread.getState());

        thread.join();

        System.out.printf("[4] State: %s \n", thread.getState());
    }
}
