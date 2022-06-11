package com.example.moviepal.repository;

import com.example.moviepal.model.FavoriteListMovie;
import com.example.moviepal.model.User;
import com.example.moviepal.model.WatchedListMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchedListMovieRepository extends JpaRepository<WatchedListMovie, Integer> {
    @Query("SELECT w FROM WatchedListMovie w where w.movieId = ?1 and w.user = ?2")

    Optional<WatchedListMovie> findByUserAndMovieId(String movieid, User user);
    Optional<WatchedListMovie> findByUser(User user);
    Optional<List<WatchedListMovie>> findAllByUser(User user);
    @Query("SELECT w FROM WatchedListMovie w where w.movieId = ?1 and w.user = ?2")

    Optional<WatchedListMovie> isMovieInList(String movieid,User user);
    @Query("SELECT w FROM WatchedListMovie w where w.user = ?1")
    List<WatchedListMovie> isList(User user);
}
