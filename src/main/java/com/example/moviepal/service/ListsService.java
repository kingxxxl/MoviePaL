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

        public WishListMovie getWishListByUserId(Integer id) {
            logger.info("calling getWishListByUserId for a list by user id");
            User user = userService.getUser(id);
            logger.info("getting the User by id");
            Optional<WishListMovie> wishListMovie = wishListMovieRepository.findByUser(user);
            if (wishListMovie.isPresent()){
                logger.info("list was found" + wishListMovie);
                return wishListMovie.get();
            }else {
                logger.info("list was NOT found, creating one using the given user");
                WishListMovie newList = new WishListMovie();
                newList.setListId(1);
                newList.setUser(user);
                logger.info("list was created: "+newList);
                wishListMovieRepository.save(newList);
                logger.info("list was saved: "+newList);
                return newList;
            }
        }

        public void saveWish(WishListMovie wishListMovie){
        wishListMovieRepository.save(wishListMovie);
        }
}
