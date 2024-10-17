package com.incetutku;

import com.incetutku.model.Account;
import com.incetutku.worker.AccountHolder;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Account account = new Account();
        AccountHolder accountHolder = new AccountHolder(account);
        Thread thread1 = new Thread(accountHolder);
        Thread thread2 = new Thread(accountHolder);
        thread1.setName("Jack");
        thread2.setName("Martin");

        thread1.start();
        thread2.start();
    }
}
