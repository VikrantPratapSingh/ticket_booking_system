package com.ticketmanagement.exceptions;

public class TicketInformationException extends Exception{
    public TicketInformationException() {
        super("Issue in creating booking Exception");
    }
    public TicketInformationException(String message) {
        super(message);
    }
}
