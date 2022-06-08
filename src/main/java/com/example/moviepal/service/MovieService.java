package com.example.moviepal.service;


import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.NoSuchFoundException;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.UserRepository;
import com.example.moviepal.repository.WishListMovieRepository;
import com.example.moviepal.service.MovieApi.MovieOMDBapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    @RequiredArgsConstructor
    public class MovieService {
    Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final MovieOMDBapi movieOMDBapi;
    private final ListsService listsService;
    private final UserRepository userRepository;
    private final WishListMovieRepository wishListMovieRepository;

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
        } catch (JsonProcessingException e) {
            logger.warn("no movie with that name");
            throw new NoSuchFoundException("No movie was found with that name!");
        }
    }

    public List<Movie> findBySearchName(String name) {
        logger.info("starting findBySearchName in MovieService");

        try {
            logger.info("calling movieOMDBapi.findBySearchName using the name");
            return movieOMDBapi.findBySearchName(name);
        } catch (Exception e) {
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

    public void addMovieByNameToWish(String name) {
        logger.info("calling addMovieByNameToWish with name of the movie");
        User user = new User();
        user.setId(12);
        user.setPassword("sa");
        user.setUsername("sa");
        logger.info("saving a temp user");
        userRepository.save(user);
        logger.info("calling to get the movie by the name");
        Movie movie= findByName(name);
        logger.info("movie was called: "+ movie);

        Integer userIdTemp = 12;
        logger.info("calling listsService for a list by user id");
        WishListMovie curr = listsService.getWishListByUserId(userIdTemp);
        logger.info("The list returned is: "+ curr);
        logger.info("movie.getId() is : "+ movie.getId());
        curr.setMovieId(movie.getId());
        wishListMovieRepository.save(curr);
    }
}
//
//
//    public List<Movie> findBySearchYear(String year) {
//        logger.info("starting findBySearchYear in MovieService");
//
//        try {
//            logger.info("calling movieOMDBapi.findBySearchYear using the name");
//            return movieOMDBapi.findBySearchYear(year);
//        }
//        catch (Exception e){
//            logger.warn("no search result was found");
//            throw new NoSuchFoundException("No movie was found with that year!");
//        }
//    }
//}