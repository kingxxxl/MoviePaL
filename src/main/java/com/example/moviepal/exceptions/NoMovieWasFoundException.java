package com.example.moviepal.exceptions;

public class NoMovieWasFoundException extends RuntimeException {
    public NoMovieWasFoundException(String message) {
        super(message);
    }
    public NoMovieWasFoundException() {
    }
}