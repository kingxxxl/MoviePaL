package com.example.moviepal.repository;

import com.example.moviepal.model.User;
import com.example.moviepal.model.FavoriteListMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteListMovieRepository extends JpaRepository<FavoriteListMovie, Integer> {

    Optional<FavoriteListMovie> findByUser(User user);
    Optional<List<FavoriteListMovie>> findAllByUser(User user);
    @Query("SELECT w FROM FavoriteListMovie w where w.movieId = ?1 and w.user = ?2")

    Optional<FavoriteListMovie> isMovieInList(String movieid,User user);
    @Query("SELECT w FROM FavoriteListMovie w where w.user = ?1")
    List<FavoriteListMovie> isList(User user);
}
