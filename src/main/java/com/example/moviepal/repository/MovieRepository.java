package com.example.moviepal.repository;

import com.example.moviepal.model.Movie;
import com.example.moviepal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
