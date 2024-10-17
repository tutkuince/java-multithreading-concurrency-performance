package com.incetutku.worker;

import com.incetutku.model.Account;

public class AccountHolder implements Runnable {
    private Account account;

    public AccountHolder(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 4; i++) {
            makeWithdrawal(2000);
            if (account.getBalance() < 0) {
                System.out.println("Account is overdrawn");
            }
        }
    }

    private synchronized void makeWithdrawal(int withdrawAmount) {
        if (account.getBalance() >= withdrawAmount) {
            System.out.printf("%s is going to withdraw $%s \n", Thread.currentThread().getName(), withdrawAmount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.withdraw(withdrawAmount);
            System.out.printf("%s completes the withdraw of %s \n", Thread.currentThread().getName(), withdrawAmount);
        } else {
            System.out.printf("Not enough in account for %s to withdraw %s \n", Thread.currentThread().getName(), account.getBalance());
        }

    }
}
