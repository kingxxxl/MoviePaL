package com.example.moviepal.repository;

import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListMovieRepository extends JpaRepository<WishListMovie, Integer> {

    Optional<WishListMovie> findByUser(User user);

}
