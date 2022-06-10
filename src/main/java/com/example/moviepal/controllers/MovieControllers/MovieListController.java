package com.example.moviepal.controllers.MovieControllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.service.ListsService;
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

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MovieListController {

    final private MovieService movieService;
    final private ListsService listsService;
    final private UserService userService;
    Logger logger = LoggerFactory.getLogger(MovieListController.class);

    @GetMapping("/wishlist")
    public ResponseEntity<List<Movie>> getWishList(){
        logger.info("Starting user/wishlist in getWishList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();
        Integer userid = userService.findUserIdByName(loggedUserName);
        logger.info("User doing the call is: "+loggedUserName+" with id: "+userid);
        User user = userService.getUserById(userid);
        logger.info("User was found: "+user.getUsername());
        List<Movie> movies = listsService.getWishListByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }
}
