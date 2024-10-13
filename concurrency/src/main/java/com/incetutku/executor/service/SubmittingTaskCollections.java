package com.incetutku.executor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SubmittingTaskCollections {
    // Single Thread Executors will accept tasks sequentially in the order they are submitted
    // private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    // with 4 threads to share the work, there is no guarantee which letter will appear first
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static List<Callable<String>> callables = new ArrayList<>();

    public static void main(String[] args) {
        callables.add(() -> "A"); // Adding Callable tasks
        callables.add(() -> "B"); // Adding Callable tasks
        callables.add(() -> "C"); // Adding Callable tasks
        callables.add(() -> "D"); // Adding Callable tasks

        // invokeAny();
        invokeAll();
    }

    public static void invokeAny() {
        try {
            // submitting a collection of tasks
            // executes synchronously and returns when one of the tasks has completed, all other tasks are cancelled
            // Note: Single thread executor will always execute the first task submitted.
            String result = executorService.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        System.out.println("Always at the end");
    }

    public static void invokeAll() {
        try {
            // submitting a collection of tasks
            // executes synchronously and returns when all of the tasks are completed
            // order is maintained i.e. the result for callables.get(0) goes into list.get(0)
            List<Future<String>> list = executorService.invokeAll(callables);
            for (Future<String> future: list) {
                System.out.println(future.get()); // A B C D in order
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
