package com.example.moviepal.service;


import com.example.moviepal.exceptions.NoMovieListWasFoundException;
import com.example.moviepal.model.FavoriteListMovie;
import com.example.moviepal.model.outDB.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.FavoriteListMovieRepository;
import com.example.moviepal.repository.WishListMovieRepository;
import com.example.moviepal.service.MovieApi.MovieOMDBapi;
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
    private final FavoriteListMovieRepository favoriteListMovieRepository;
    private final UserService userService;

    public WishListMovie getWishListById(Integer id) {
        return wishListMovieRepository.findById(id).get();
    }

        public List<Movie> getWishListByUser(User user) {
            logger.info("calling getWishListByUserId for a list by user id: "+user.getId());
            List<WishListMovie> wishListMovie = wishListMovieRepository.isList(user);
            if (!wishListMovie.isEmpty()){
                logger.info("list was found and size is: "+wishListMovie.size());
                List<Movie> movies = new ArrayList<>();
                for (WishListMovie wish:wishListMovie) {
                    Movie movie = movieOMDBapi.findById(wish.getMovieId());
                    movies.add(movie);
                }
                return movies;
            }else {
                logger.info("list was NOT found, creating one using the given user");
                throw new NoMovieListWasFoundException("No wishlist for this user yet, add some movies first!");
            }
        }
    public List<Movie> getFavoriteListByUser(User user) {
        logger.info("calling getFavoriteListByUserId for a list by user id: "+user.getId());
        List<FavoriteListMovie> favoriteListMovie = favoriteListMovieRepository.isList(user);
        if (!favoriteListMovie.isEmpty()){
            logger.info("list was found and size is: "+favoriteListMovie.size());
            List<Movie> movies = new ArrayList<>();
            for (FavoriteListMovie favorite:favoriteListMovie) {
                Movie movie = movieOMDBapi.findById(favorite.getMovieId());
                movies.add(movie);
            }
            return movies;
        }else {
            logger.info("list was NOT found, creating one using the given user");
            throw new NoMovieListWasFoundException("No favoritelist for this user yet, add some movies first!");
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
    public boolean isMovieInUserFavoriteList(Movie movie, User user) {
        Optional<FavoriteListMovie> movieid = favoriteListMovieRepository.isMovieInList(movie.getId(),user);
        if (movieid.isPresent()){
            return true;
        }
        return false;
    }
}
