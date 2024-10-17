package com.incetutku.model;

public class TicketCounter {
    private int availableSeats = 3;

    public synchronized void bookTicket(String pName, int numberOfSeats) {
        if ((availableSeats >= numberOfSeats) && (numberOfSeats > 0)) {
            System.out.printf("Hi, %s: %s Seats booked successfully \n", pName, numberOfSeats);
            availableSeats = availableSeats - numberOfSeats;
        } else {
            System.out.printf("Hi, %s: Seats Not Available \n", pName);
        }
    }
}
