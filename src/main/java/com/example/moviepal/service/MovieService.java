package com.example.moviepal.service;


import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.MovieAlreadyInTheListException;
import com.example.moviepal.exceptions.NoMovieWasFoundException;
import com.example.moviepal.exceptions.NoSuchFoundException;
import com.example.moviepal.model.FavoriteListMovie;
import com.example.moviepal.model.WatchedListMovie;
import com.example.moviepal.model.outDB.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.FavoriteListMovieRepository;
import com.example.moviepal.repository.UserRepository;
import com.example.moviepal.repository.WatchedListMovieRepository;
import com.example.moviepal.repository.WishListMovieRepository;
import com.example.moviepal.service.MovieApi.MovieOMDBapi;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    @RequiredArgsConstructor
    public class MovieService {
    Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final MovieOMDBapi movieOMDBapi;
    private final ListsService listsService;
    private final WishListMovieRepository wishListMovieRepository;
    private final FavoriteListMovieRepository favoriteListMovieRepository;
    private final WatchedListMovieRepository watchedListMovieRepository;

    public Movie findByName(String name) {
        logger.info("starting findByName in MovieService");

        try {
            logger.info("calling movieOMDBapi.findByTitle using the name");
            Movie movie = movieOMDBapi.findByTitle(name);
            if (movie.getId() == null){
                logger.warn("no movie with that name");
                throw new NoMovieWasFoundException("No movie was found with that name!");
            }
            return movie;
        } catch (Exception e) {
            logger.warn("no movie with that name");
            throw new NoMovieWasFoundException("No movie was found with that name!");
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

    public void addMovieByNameToWish(String name, User user) {
        logger.info("starting addMovieByNameToWish with name of the movie");
        Movie movie= findByName(name);
        logger.info("movie was called: "+ movie);
        if(!listsService.isMovieInUserWishList(movie,user)){
            WishListMovie newWishList = new WishListMovie();
            newWishList.setUser(user);
            newWishList.setMovieId(movie.getId());
            wishListMovieRepository.save(newWishList);
        }else {
            throw new MovieAlreadyInTheListException("Movie in the list already!");
        }
    }
    public void addMovieByNameToFavorite(String name, User user) {
        logger.info("starting addMovieByNameToFavorite with name of the movie");
        Movie movie= findByName(name);
        logger.info("movie was called: "+ movie);
        if(!listsService.isMovieInUserFavoriteList(movie,user)){
            FavoriteListMovie newFavoriteList = new FavoriteListMovie();
            newFavoriteList.setUser(user);
            newFavoriteList.setMovieId(movie.getId());
            favoriteListMovieRepository.save(newFavoriteList);
        }else {
            throw new MovieAlreadyInTheListException("Movie in the list already!");
        }
    }
    public void addMovieByNameToWatched(String name, User user) {
        logger.info("starting addMovieByNameToWatched with name of the movie");
        Movie movie= findByName(name);
        logger.info("movie was called: "+ movie);
        if(!listsService.isMovieInUserWatchedList(movie,user)){
            WatchedListMovie newWatchedList = new WatchedListMovie();
            newWatchedList.setUser(user);
            newWatchedList.setMovieId(movie.getId());
            watchedListMovieRepository.save(newWatchedList);
        }else {
            throw new MovieAlreadyInTheListException("Movie in the list already!");
        }
    }

    public void removeMovieByNameFromFavorite(String name, User user) {
        logger.info("starting removeMovieByNameFromFavorite with name of the movie");
        List<Movie> favoriteListMovie = listsService.getFavoriteListByUser(user);
        logger.info("favoriteListMovie was obtained using the user");
        for (Movie m: favoriteListMovie) {
            if(name.equalsIgnoreCase(m.getTitle())){
                logger.info("movie is in the list");
                Optional<FavoriteListMovie> movieToBeRemoved = favoriteListMovieRepository.findByUserAndMovieId(m.getId(),user);
                if (movieToBeRemoved.isPresent()){
                    logger.info("movie was found, now deleting it");
                    favoriteListMovieRepository.delete(movieToBeRemoved.get());
                    return;
                }
                logger.error("cant find the movie to be removed");
               throw new RuntimeException("something went wrong");
            }
        }
        throw new NoMovieWasFoundException("no movie with that name in your favorite list!");

    }
}
