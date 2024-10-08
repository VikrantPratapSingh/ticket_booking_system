package com.ticketmanagement.exceptions;

public class SeatInformationException extends Exception{
    public SeatInformationException() {
        super("No seats available");
    }
    public SeatInformationException(String message) {
        super(message);
    }
}
