package com.example.moviepal.exceptions;

public class NoSuchFoundException extends RuntimeException {
    public NoSuchFoundException(String message) {
        super(message);
    }
}