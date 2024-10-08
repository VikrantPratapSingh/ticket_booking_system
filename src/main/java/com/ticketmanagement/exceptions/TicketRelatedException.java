package com.ticketmanagement.exceptions;

public class TicketRelatedException extends Exception{
    public TicketRelatedException() {
        super("Issue in creating booking Exception");
    }
    public TicketRelatedException(String message) {
        super(message);
    }
}
