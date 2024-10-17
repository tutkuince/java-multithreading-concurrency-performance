package com.incetutku;

import com.incetutku.model.TicketCounter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TicketCounter ticketCounter = new TicketCounter();
        TicketBookingThread t1 = new TicketBookingThread(ticketCounter, "John", 2);
        TicketBookingThread t2 = new TicketBookingThread(ticketCounter, "Terry", 2);

        t1.start();
        t2.start();
    }
}
