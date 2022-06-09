package com.example.moviepal.exceptions;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException(String message) {
        super(message);
    }
    public NoUserFoundException() {
    }
}