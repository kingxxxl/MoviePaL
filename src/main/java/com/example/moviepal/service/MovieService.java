package com.example.moviepal.service;


import com.example.moviepal.advice.ControllerAdviseHandler;
import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.NoSuchFoundException;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.Search;
import com.example.moviepal.repository.MovieApi.MovieOMDBapi;
import com.example.moviepal.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    @RequiredArgsConstructor
    public class MovieService {
    Logger logger = LoggerFactory.getLogger(ControllerAdviseHandler.class);
        private final MovieRepository movieRepository;
        private final MovieOMDBapi movieOMDBapi;

//         public Movie findByID(Integer id) {
//        Optional<Movie> movie = movieRepository.findById(id);
//        if (movie.isPresent()){
//            return movieRepository.findById(id).get();
//        }else {
//            throw new InvalidIdException("Invalid id");
//        }
//    }
    public Movie findByName(String name) {
        logger.info("starting findByName in MovieService");

        try {
            logger.info("calling movieOMDBapi.findByTitle using the name");
            Movie movie = movieOMDBapi.findByTitle(name);
            return movie;
        }
       catch (JsonProcessingException e){
           logger.warn("no movie with that name");
           throw new NoSuchFoundException("No movie was found with that name!");        }
    }

    public List<Movie> findBySearchName(String name) {
        logger.info("starting findBySearchName in MovieService");

        try {
            logger.info("calling movieOMDBapi.findBySearchName using the name");
            return movieOMDBapi.findBySearchName(name);
        }
        catch (Exception e){
            logger.warn("no search result was found");
            throw new NoSuchFoundException("No movie was found with that name!");
        }
    }

    public Movie findById(String id) {
        logger.info("starting findById in MovieService");
        try {
            logger.info("calling movieOMDBapi.findByid using tid");
            return movieOMDBapi.findById(id);
        } catch (Exception e) {
            logger.warn("no id for this movie");
            throw new InvalidIdException("No such id!");
        }
    }
}