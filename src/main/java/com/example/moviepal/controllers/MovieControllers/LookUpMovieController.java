package com.example.moviepal.controllers.MovieControllers;

import com.example.moviepal.model.Movie;
import com.example.moviepal.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movie/lookby")
@RequiredArgsConstructor
public class LookUpMovieController {


    final private MovieService movieService;
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @GetMapping("/name/{name}")
    public ResponseEntity<Movie> byNameExact(@PathVariable String name){
        logger.info("Starting movie/lookby/movie/lookby/name in LookUpMovieController");
        logger.info("calling movieService.findByName with name");
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> byId(@PathVariable String id){
        logger.info("Starting movie/lookby/movie/lookby/id in LookUpMovieController");
        logger.info("calling movieService.findByid with id");
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findById(id));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Movie>> byNameSearch(@PathVariable String name){
        logger.info("Starting movie/lookby/search/movie/lookby/name in LookUpMovieController");
        logger.info("calling movieService.byNameSearch with name");
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findBySearchName(name));

    }


}
