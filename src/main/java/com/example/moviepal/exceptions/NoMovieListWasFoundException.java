package com.example.moviepal.exceptions;

public class NoMovieListWasFoundException extends RuntimeException {
    public NoMovieListWasFoundException(String message) {
        super(message);
    }
    public NoMovieListWasFoundException() {
    }
}