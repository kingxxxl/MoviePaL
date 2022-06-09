package com.example.moviepal.repository;

import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListMovieRepository extends JpaRepository<WishListMovie, Integer> {

    Optional<WishListMovie> findByUser(User user);
    Optional<List<WishListMovie>> findAllByUser(User user);
    @Query("SELECT w FROM WishListMovie w where w.movieId = ?1 and w.user = ?2")

    Optional<WishListMovie> isMovieInList(String movieid,User user);

}
