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
@RequestMapping("movie/add")
@RequiredArgsConstructor
public class AddMovieController {


    final private MovieService movieService;
    final private UserService userService;

    Logger logger = LoggerFactory.getLogger(AddMovieController.class);

    @PostMapping("/name/wishlist/{name}")
    public ResponseEntity<API> byNameToWishList(@PathVariable String name){
        logger.info("Starting movie/add/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.addMovieByNameToWish(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was added to your wish list!",201));
    }
    @PostMapping("/name/favorite-list/{name}")
    public ResponseEntity<API> byNameToFavoriteList(@PathVariable String name){
        logger.info("Starting movie/add/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.addMovieByNameToFavorite(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was added to your favorite list!",201));
    }
    @PostMapping("/name/watched-list/{name}")
    public ResponseEntity<API> byNameToWatchedList(@PathVariable String name){
        logger.info("Starting movie/add/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        movieService.addMovieByNameToWatched(name, user);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was added to your watched list!",201));
    }

//    @GetMapping("/id/{id}")
//    public ResponseEntity<Movie> byId(@PathVariable String id){
//        logger.info("Starting movie/lookby/movie/lookby/id in LookUpMovieController");
//        logger.info("calling movieService.findByid with id");
//        return ResponseEntity.status(HttpStatus.OK).body(movieService.findById(id));
//    }
//
//    @GetMapping("/search/name/{name}")
//    public ResponseEntity<List<Movie>> byNameSearch(@PathVariable String name){
//        logger.info("Starting movie/lookby/search/movie/lookby/name in LookUpMovieController");
//        logger.info("calling movieService.byNameSearch with name");
//        return ResponseEntity.status(HttpStatus.OK).body(movieService.findBySearchName(name));
//
//    }


}
