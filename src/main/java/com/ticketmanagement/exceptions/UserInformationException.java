package com.ticketmanagement.exceptions;

public class UserInformationException extends Exception{
    public UserInformationException() {
        super("No user Found");
    }
    public UserInformationException(String message) {
        super(message);
    }
}
