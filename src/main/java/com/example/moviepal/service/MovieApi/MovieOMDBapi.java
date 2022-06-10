package com.example.moviepal.service.MovieApi;

import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.outDB.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieOMDBapi {
    Logger logger = LoggerFactory.getLogger(MovieOMDBapi.class);
    private final String URL_FIND_SINGLE_BY_NAME = "https://www.omdbapi.com/?apikey=c2f1c494&t=";
    private final String URL_FIND_SINGLE_BY_ID = "https://www.omdbapi.com/?apikey=c2f1c494&i=";
    private final String URL_SEARCH_LIST_BY_NAME = "https://www.omdbapi.com/?apikey=c2f1c494&s=";
    private final String URL_SEARCH_LIST_BY_NAME_YEAR = "https://www.omdbapi.com/?apikey=c2f1c494&y=";
    public Movie findByTitle(String name) throws JsonProcessingException {
        logger.info("Starting findByTitle");

        String url = URL_FIND_SINGLE_BY_NAME + name;

        logger.info("URL was obtained:"+url);

        String apiJson = getBodyFromURL(url);

        Movie movie = getMovieFromSingleJson(apiJson);

        logger.info("The movie was mapped with the given json: "+movie);
        logger.info("returning the movie object");

        return movie;

    }
    public Movie findById(String id) throws InvalidIdException {
        logger.info("Starting findById");
        String url = URL_FIND_SINGLE_BY_ID + id;

        logger.info("URL was obtained:"+url);

        String apiJson = getBodyFromURL(url);
        try {
            Movie movie = getMovieFromSingleJson(apiJson);
            logger.info("The movie was mapped with the given json: "+movie);


            if (movie.getId() == null){
                logger.warn("no id for this movie");
                throw  new InvalidIdException();
            }
            logger.info("returning the movie object");
            return movie;
        }catch (JsonProcessingException e){
            throw new InvalidIdException("No movie with that id");
        }


    }
    public List<Movie> findBySearchName(String name) throws JsonProcessingException {
        logger.info("Starting findBySearchName");

        String url = URL_SEARCH_LIST_BY_NAME +name;
        logger.info("URL was obtained:"+url);
        String apiJson = getBodyFromURL(url);
        List<Movie> movies = getListMovieFromJson(apiJson);
        logger.info("The movies were mapped with the given json: "+movies);


        return movies;

    }
//    public List<Movie> findBySearchYear(String year) throws JsonProcessingException {
//        logger.info("Starting findBySearchYear");
//
//        String url = URL_SEARCH_LIST_BY_YEAR +year;
//        logger.info("URL was obtained:"+url);
//        String apiJson = getBodyFromURL(url);
//        List<Movie> movies = getListMovieFromJson(apiJson);
//        logger.info("The movies were mapped with the given json: "+movies);
//        return movies;
//    }

    private String getBodyFromURL(String url){
        logger.info("Starting getBodyFromURL");

        //getting the json string from the url
        WebClient webClient = WebClient.create(url);
        Mono<String> body = webClient.get().retrieve().bodyToMono(String.class);
        String resultJsonAsString = body.block();
        logger.info("The body from the url was obtained:"+resultJsonAsString);
        return resultJsonAsString;

    }

    private Movie getMovieFromSingleJson(String movieJson) throws JsonProcessingException {
        logger.info("Starting getMovieFromSingleJson");
        //mapping the json string to java object
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        logger.info("returning movie object from getMovieFromSingleJson: ");
        return objectMapper.readValue(movieJson, Movie.class);
    }

    private List<Movie> getListMovieFromJson(String movieJson) throws JsonProcessingException {
        logger.info("Starting getListMovieFromJson");
        //mapping the json string to java object
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);


        Search search = objectMapper.readValue(movieJson, Search.class);
        logger.info("search string is: "+search);

        List<Movie> movies = getListFromSearch(search);


//        List<Movie> movies = objectMapper.readValue(movieJson,new TypeReference<List<Movie>>(){});

        logger.info("returning movie list using found object from getListMovieFromJson:");
        return movies;
    }


    List<Movie> getListFromSearch(Search search){
        logger.info("starting getListFromSearch");
        List<Movie> result = new ArrayList<>();
        for (Search c: search.getSearch()) {
        Movie movie = new Movie();
        movie.setId(c.getId());
        movie.setTitle(c.getTitle());
        movie.setYear(c.getYear());
        movie.setType(c.getType());
        result.add(movie);
        }
        logger.info("fished  creating movie list result in getListFromSearch");

        return result;
    }
}
