package org.example.exceptions;

public class UserNotFoundException extends Exception {
    private final String email;

    public UserNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
