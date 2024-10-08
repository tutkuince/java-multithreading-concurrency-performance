package com.incetutku._01.thread.creation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here
    private final List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        List<Thread> threads = new ArrayList<>(tasks.size());

        tasks.forEach(task -> {
            Thread thread = new Thread(task);
            threads.add(thread);
        });

        threads.forEach(Thread::start);
    }
}
