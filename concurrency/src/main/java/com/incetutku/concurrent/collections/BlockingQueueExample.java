package com.incetutku.concurrent.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // regular Queue Methods
        queue.offer("Red");
        queue.offer("Green");
        queue.offer("Blue");                 // Red, Green, Blue
        System.out.println(queue.poll());       // Red | Head -> Green, Blue <- Tail
        System.out.println(queue.peek());       // Green | Head -> Green, Blue <- Tail

        // special BlockingQueue methods
        try {
            // block is queue full...
            queue.offer("White", 100, TimeUnit.MILLISECONDS); // head -> [Green, Blue, White] <- tail
            // block queue is empty
            queue.poll(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);
    }
}
