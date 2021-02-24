package com.champion.academy.regististion.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user_not_found_in_database) {
        super(user_not_found_in_database);
    }
}
