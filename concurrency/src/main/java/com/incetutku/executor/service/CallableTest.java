package com.incetutku.executor.service;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool consisting of one thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit the Callable task (asynchronously) to the executor service
        // and store the Future object
        Future<Integer> future = executorService.submit(() -> 3 + 5); //  V call()

        try {
            System.out.println("The answer is: " + future.get(500, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepted
        executorService.shutdown();
    }
}
