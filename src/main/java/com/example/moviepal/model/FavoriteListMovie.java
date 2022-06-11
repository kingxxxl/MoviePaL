package com.example.moviepal.model;

import com.example.moviepal.model.outDB.Movie;
import com.example.moviepal.model.outDB.MovieList;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class FavoriteListMovie implements MovieList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listId;

    private String movieId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public List<Movie> getMovies(String userId) {
        return null;
    }

    @Override
    public Movie getMovieById(String id) {
        return null;
    }

    @Override
    public Movie getMovieByName(String name) {
        return null;
    }

    @Override
    public boolean isListEmpty() {
        return false;
    }

    @Override
    public Integer listSize() {
        return null;
    }

    @Override
    public void addMovieToList(Movie movie) {

    }

    @Override
    public void removeMovieFromList(Movie movie) {

    }
}
