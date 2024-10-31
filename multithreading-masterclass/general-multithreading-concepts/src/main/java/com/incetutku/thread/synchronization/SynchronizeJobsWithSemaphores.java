package com.incetutku.thread.synchronization;

import java.util.concurrent.Semaphore;

public class SynchronizeJobsWithSemaphores {
    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {
        Executor executor = new Executor();

        executor.submit(new Job(4000));
        executor.submit(new Job(5000));
        executor.submit(new Job(3000));
    }

    static class Executor {
        public void submit(Job job) throws InterruptedException {

            System.out.printf("Launch job %s\n", job.getWork());
            semaphore.acquire();

            Thread thread = new Thread(() -> {
                try {
                    System.out.printf("Executing job %s\n", job.getWork());
                    Thread.sleep(job.getWork());

                    semaphore.release();
                    System.out.printf("Job finished with id %s\n", job.getWork());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    static class Job {
        private final int work;

        public Job(int work) {
            this.work = work;
        }

        public int getWork() {
            return work;
        }
    }
}
