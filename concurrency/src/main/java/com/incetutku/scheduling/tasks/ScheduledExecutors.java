package com.incetutku.scheduling.tasks;

import java.util.concurrent.*;

public class ScheduledExecutors {
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        // schedule();
        // scheduleWithFixedDelay();
        scheduleAtFixedRate();
    }

    public static void schedule() {
        System.out.println("Start...");
        Future<String> future = service.schedule(() -> "A", 2, TimeUnit.SECONDS);
        try {
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
        System.out.println("Always at the end");
    }

    public static void scheduleWithFixedDelay() {
        System.out.println("Start...");
        // 300 milliseconds is the time to wait from when the previous task finishes to starting the next task
        // -> previous task finishes - wait 300milliseconds - start next task
        final long INITIAL_DELAY = 2000, WAIT_PERIOD_AFTER_PREVIOUS_TASK_FINISHED = 300;
        service.scheduleWithFixedDelay(() -> System.out.println("Thread id: " + Thread.currentThread().getId()),
                INITIAL_DELAY, WAIT_PERIOD_AFTER_PREVIOUS_TASK_FINISHED, TimeUnit.MILLISECONDS);

        // service.shutdown(); if I shut it down, nothing happens
    }

    public static void scheduleAtFixedRate() {
        System.out.println("Start...");
        // Assuming 500 milliseconds is the time after the initial delay to wait to start the next task and so forth
        // => initial task starts at 2000 milliseconds                      = 2000 milliseconds
        //  task 2 starts at 2000 milliseconds + 500 milliseconds           = 2500 milliseconds
        //  task 3 starts at 2000 milliseconds + (500 milliseconds * 2)     = 3000 milliseconds
        //  task 4 starts at 2000 milliseconds + (500 milliseconds * 3)     = 3500 milliseconds and so on
        final long INITIAL_DELAY = 2000, WAIT_PERIOD_BEFORE_STARTING_NEXT_TASK = 300;
        service.scheduleAtFixedRate(() -> System.out.println("Thread Id: " + Thread.currentThread().getId()),
                INITIAL_DELAY, WAIT_PERIOD_BEFORE_STARTING_NEXT_TASK, TimeUnit.MILLISECONDS);

        // service.shutdown(); if I shut it down, nothing happens
    }
}
