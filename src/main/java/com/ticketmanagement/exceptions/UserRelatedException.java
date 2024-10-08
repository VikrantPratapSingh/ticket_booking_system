package com.ticketmanagement.exceptions;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public class UserRelatedException extends Exception{
    public UserRelatedException() {
        super("No user Found");
    }
    public UserRelatedException(String message) {
        super(message);
    }
}
