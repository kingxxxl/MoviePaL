package com.example.moviepal.model.outDB;

import java.util.List;

public interface MovieList {
    public List<Movie> getMovies(String userId);
    public Movie getMovieById(String id);
    public Movie getMovieByName(String name);
    public boolean isListEmpty();
    public Integer listSize();
    public void addMovieToList(Movie movie);
    public void removeMovieFromList(Movie movie);
}
