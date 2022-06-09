package com.example.moviepal.service;


import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.NoSuchFoundException;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.WishListMovieRepository;
import com.example.moviepal.service.MovieApi.MovieOMDBapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
    @RequiredArgsConstructor
    public class ListsService {
    Logger logger = LoggerFactory.getLogger(ListsService.class);
    private final MovieOMDBapi movieOMDBapi;
    private final WishListMovieRepository wishListMovieRepository;
    private final UserService userService;

    public WishListMovie getWishListById(Integer id) {
        return wishListMovieRepository.findById(id).get();
    }

        public WishListMovie getWishListByUser(User user) {
            logger.info("calling getWishListByUserId for a list by user id: "+user.getId());
            Optional<WishListMovie> wishListMovie = wishListMovieRepository.findByUser(user);
            if (wishListMovie.isPresent()){
                logger.info("list was found" + wishListMovie);
                return wishListMovie.get();
            }else {
                logger.info("list was NOT found, creating one using the given user");
                WishListMovie newList = new WishListMovie();
                newList.setUser(user);
                logger.info("list was created: "+newList);
                wishListMovieRepository.save(newList);
                logger.info("list was saved: "+newList);
                return newList;
            }
        }
    public List<WishListMovie> getAllWishListByUser(User user){
        logger.info("calling getAllWishListByUser for a list by user id: "+user.getId());
       Optional<List<WishListMovie>> listMovies = wishListMovieRepository.findAllByUser(user);
       if (listMovies.isPresent()){
           return listMovies.get();
       }
        List<WishListMovie> listMovies1 = new ArrayList<>();
        return listMovies1;
    }

        public void saveWish(WishListMovie wishListMovie){
        wishListMovieRepository.save(wishListMovie);
        }

    public boolean isMovieInUserWishList(Movie movie, User user) {
        Optional<WishListMovie> movieid = wishListMovieRepository.isMovieInList(movie.getId(),user);

        if (movieid.isPresent()){
            return true;
        }
        return false;
    }
}
