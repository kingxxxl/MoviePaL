package com.example.moviepal.controllers.MovieControllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.advice.ControllerAdviseHandler;
import com.example.moviepal.model.Movie;
import com.example.moviepal.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie/add")
@RequiredArgsConstructor
public class AddMovieController {


    final private MovieService movieService;
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @PostMapping("/name/{name}")
    public ResponseEntity<API> byNameToWishList(@PathVariable String name){
        logger.info("Starting movie/add/name in LookUpMovieController");
        logger.info("calling movieService.addByName with name");
        movieService.addMovieByNameToWish(name);
        return ResponseEntity.status(HttpStatus.OK).body(new API("movie was added to the list!",201));
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
