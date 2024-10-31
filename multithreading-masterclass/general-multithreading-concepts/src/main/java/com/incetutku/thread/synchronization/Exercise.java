package com.incetutku.thread.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Here we have a code which has a simple race condition. Here are the main steps for this coding exercise:
 * - Identify the race condition by first identifying the shared state between the threads
 * - Try to use the synchronization tools we learned in this chapter, to solve the race condition
 * - Play with the code - try to see which synchronization tool is the one which fits best on this use-case
 * - You can modify anywhere in the code to solve the race condition.
 */
public class Exercise {
    private static final int NO_TRANSACTIONS = 1000;
    private static final int TRANSACTION_AMOUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        int finalBalance = getFinalBalance();
        System.out.println(finalBalance);
    }

    public static int getFinalBalance() throws InterruptedException {
        BankAccount account = new BankAccount();

        // Let's say we have 2 users which are using the same account
        User user1 = new User(account);
        User user2 = new User(account);

        Thread t1 = new Thread(() -> {
            // User 1 is adding money into the account
            for (int i = 0; i < NO_TRANSACTIONS; i++) {
                user1.getAccount().depositMoney(TRANSACTION_AMOUNT);
            }
        });

        Thread t2 = new Thread(() -> {
            // User 2 is withdrawing money into the account (in the same time)
            for (int i = 0; i < NO_TRANSACTIONS; i++) {
                user2.getAccount().withdrawMoney(TRANSACTION_AMOUNT);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // At the end we should have the balance 0, right?
        return user1.getAccount().getBalance();
    }
}

class BankAccount {
    private int balance;
    private final Lock lock = new ReentrantLock();
    private static final Object obj = new Object();

    public BankAccount() {
        balance = 0;
    }

    public void depositMoney(int amount) {
        lock.lock();
        balance += amount;
        lock.unlock();
    }

    public void withdrawMoney(int amount) {
        lock.lock();
        balance -= amount;
        lock.unlock();
    }

    public int getBalance() {
        return balance;
    }
}

class User {
    private final BankAccount account;

    public User(BankAccount account) {
        this.account = account;
    }

    public BankAccount getAccount() {
        return account;
    }
}