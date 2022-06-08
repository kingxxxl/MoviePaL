package com.example.moviepal.model;

import com.example.moviepal.model.outDB.MovieList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class WishListMovie implements MovieList {
    @Id
    @Column(unique = true)
    private Integer listId;

    private String movieId;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;






//    @OneToOne(mappedBy = "wishListMovie")
//    private User user;

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
