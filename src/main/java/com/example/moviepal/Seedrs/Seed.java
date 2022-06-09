package com.example.moviepal.Seedrs;

import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.UserRepository;
import com.example.moviepal.repository.WishListMovieRepository;
import com.example.moviepal.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(Seed.class);
    private final UserRepository userRepository;
    private final WishListMovieRepository wishListMovieRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {
        logger.info("starting loadUserData from Seed");
        List<String> movies = new ArrayList<>();
        movies.add("tt2446980");
        movies.add("tt0206314");
        movies.add("tt0107282");
        if (userRepository.count() == 0) {
            for (int i = 0; i < 3; i++) {

                User user = new User();
                user.setUsername("TEMP"+i);
                user.setPassword("a1234567a"+i);
                String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
                user.setPassword(hashedPassword);
                user.setRole("Admin");

                userRepository.save(user);

//                WishListMovie wishListMovie = new WishListMovie();
//                wishListMovie.setUser(user);
//                wishListMovie.setListId(i);
//                wishListMovie.setMovieId(movies.get(i));
//                System.out.println("wish is:"+wishListMovie);
//
//                wishListMovieRepository.save(wishListMovie);
            }
            logger.info("Seed completed: number of rows added: " + userRepository.count());
        }
        User user = new User();
        user.setId(201);
        user.setUsername("TEMP"+201);
        user.setPassword("a1234567a"+201);
        String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("User");

        userRepository.save(user);
        logger.info("Seed completed: number of row added: " + 1);

    }
}