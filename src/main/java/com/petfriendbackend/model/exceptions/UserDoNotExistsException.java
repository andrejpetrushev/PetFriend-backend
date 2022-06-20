package com.petfriendbackend.model.exceptions;

public class UserDoNotExistsException extends RuntimeException {
    public UserDoNotExistsException() {
        super("User do not exists!");
    }
}
