package com.incetutku.executor.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTest {
    public static void main(String[] args) {
        // create an ExecutorService with a fixed thread pool consisting of one thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // execute the Runnable task (asynchronously) - void run()
        executorService.execute(() -> System.out.println("Runnable Example"));

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepted
        executorService.shutdown();
    }
}
