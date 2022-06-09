package com.example.moviepal.exceptions;

public class MovieAlreadyInTheListException extends RuntimeException {
    public MovieAlreadyInTheListException(String message) {
        super(message);
    }
    public MovieAlreadyInTheListException() {
    }
}