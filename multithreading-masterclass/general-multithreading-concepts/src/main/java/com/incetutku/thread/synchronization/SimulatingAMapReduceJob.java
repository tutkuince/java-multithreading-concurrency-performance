package com.incetutku.thread.synchronization;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class SimulatingAMapReduceJob {
    private static final String INPUT = "a friend in need is a friend indeed";

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(2);

    private static final List<Map.Entry<String, Integer>> intermediateResult = Collections.synchronizedList(new ArrayList<>());
    private static final List<List<Map.Entry<String, Integer>>> reducersInput = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        List<String> inputList = Arrays.asList(INPUT.split(" "));

        Thread thread1 = new Thread(new Mapper(inputList.subList(0, inputList.size() / 2)));
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(new Mapper(inputList.subList(inputList.size() / 2, inputList.size())));
        thread2.start();
        thread2.join();

        Thread thread3 = new Thread(new Partitioner());
        thread3.start();
        thread3.join();

        for (List<Map.Entry<String, Integer>> reducerInput : reducersInput) {
            new Thread(new Reducer(reducerInput)).start();
        }

        System.out.println("Test");
    }

    static class Mapper implements Runnable {

        private final List<String> input;

        public Mapper(List<String> input) {
            this.input = input;
        }

        @Override
        public void run() {
            for (String word : input) {
                intermediateResult.add(Map.entry(word, 1));
            }
            COUNT_DOWN_LATCH.countDown();
        }
    }

    static class Partitioner implements Runnable {

        @Override
        public void run() {

            try {
                COUNT_DOWN_LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<String> uniqueWords = intermediateResult.stream()
                    .map(Map.Entry::getKey)
                    .distinct()
                    .collect(Collectors.toList());

            for (String word : uniqueWords) {
                List<Map.Entry<String, Integer>> reducerInput = intermediateResult.stream()
                        .filter(entry -> entry.getKey().equals(word))
                        .collect(Collectors.toList());

                reducersInput.add(reducerInput);
            }
        }
    }

    static class Reducer implements Runnable {
        private final List<Map.Entry<String, Integer>> reducerInput;

        public Reducer(List<Map.Entry<String, Integer>> reducerInput) {
            this.reducerInput = reducerInput;
        }

        @Override
        public void run() {
            int S = 0;
            for (Map.Entry<String, Integer> entry : reducerInput) {
                S += entry.getValue();
            }

            System.out.printf("The word: %s -> occurrences: %s \n", reducerInput.get(0).getKey(), S);
        }
    }
}
