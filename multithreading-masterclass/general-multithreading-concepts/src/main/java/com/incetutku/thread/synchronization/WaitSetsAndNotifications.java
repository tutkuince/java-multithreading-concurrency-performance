package com.incetutku.thread.synchronization;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Wait Sets and Notifications (Producer Consumer)
 */
public class WaitSetsAndNotifications {
    private static final Object OBJECT = new Object();

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {

        private final Queue<String> queue;

        public Producer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    produceData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void produceData() throws InterruptedException {
            synchronized (queue) {
                if (queue.size() == 10) {
                    System.out.println("In producer, waiting...");
                    queue.wait();
                }
                Thread.sleep(1000);

                System.out.printf("Producing data with id: %s \n", queue.size());
                queue.add("element-" + queue.size());

                if (queue.size() == 1) {
                    queue.notify();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private final Queue<String> queue;

        public Consumer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    consumeData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consumeData() throws InterruptedException {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    System.out.println("Consumer is waiting...");
                    queue.wait();
                }
                Thread.sleep(1000);

                String data = queue.remove();
                System.out.printf("Consumed data: %s \n", data);

                if (queue.size() == 9) {
                    queue.notify();
                }
            }
        }
    }
}
