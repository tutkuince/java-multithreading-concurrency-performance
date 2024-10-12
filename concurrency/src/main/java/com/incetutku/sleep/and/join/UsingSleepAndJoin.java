package com.incetutku.sleep.and.join;

public class UsingSleepAndJoin implements Runnable {
    private String[] timerStrArray = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    public static void main(String[] args) throws InterruptedException {
        Thread timer = new Thread(new UsingSleepAndJoin());
        System.out.println("Starting 10 second count down...");
        timer.start();
        timer.join();
        System.out.println("Boom!!!");
    }

    @Override
    public void run() {
        for (int i = timerStrArray.length - 1; i >= 0; i--) {
            System.out.println(timerStrArray[i]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
