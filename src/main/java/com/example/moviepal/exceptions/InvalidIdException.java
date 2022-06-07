package com.example.moviepal.exceptions;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String message) {
        super(message);
    }
    public InvalidIdException() {
    }
}