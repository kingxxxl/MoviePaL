package com.example.moviepal.controllers.MovieControllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.model.User;
import com.example.moviepal.service.MovieService;
import com.example.moviepal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/remove")
@RequiredArgsConstructor
public class RemoveMovieController {


    final private MovieService movieService;
    final private UserService userService;

    Logger logger = LoggerFactory.getLogger(RemoveMovieController.class);

    @DeleteMapping ("/name/wish-list/{name}")
    public ResponseEntity<API> byNameToWishList(@PathVariable String name){
        logger.info("Starting movie/remove/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.removeMovieByNameFromWish(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was remove from the list!",200));
    }
    @DeleteMapping ("/name/favorite-list/{name}")
    public ResponseEntity<API> byNameToFavoriteList(@PathVariable String name){
        logger.info("Starting movie/remove/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.removeMovieByNameFromFavorite(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was remove from the list!",200));
    }
    @DeleteMapping ("/name/watched-list/{name}")
    public ResponseEntity<API> byNameToWatchedList(@PathVariable String name){
        logger.info("Starting movie/remove/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.removeMovieByNameFromWatched(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was remove from the list!",200));
    }
}
