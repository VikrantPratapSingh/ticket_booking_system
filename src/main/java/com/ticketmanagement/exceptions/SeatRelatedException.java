package com.ticketmanagement.exceptions;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public class SeatRelatedException extends Exception{
    public SeatRelatedException() {
        super("No seats available");
    }
    public SeatRelatedException(String message) {
        super(message);
    }
}
