package com.example.moviepal.Seedrs;

import com.example.moviepal.model.User;
import com.example.moviepal.model.WishListMovie;
import com.example.moviepal.repository.UserRepository;
import com.example.moviepal.repository.WishListMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final WishListMovieRepository wishListMovieRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {

        List<String> movies = new ArrayList<>();
        movies.add("tt2446980");
        movies.add("tt0206314");
        movies.add("tt0107282");
        if (userRepository.count() == 0) {
            for (int i = 0; i < 3; i++) {

                User user = new User();
                user.setId(i);
                user.setUsername("TEMP");
                user.setPassword("Temp");

                userRepository.save(user);

                WishListMovie wishListMovie = new WishListMovie();
                wishListMovie.setUser(user);
                wishListMovie.setListId(i);
                wishListMovie.setMovieId(movies.get(i));
                System.out.println("wish is:"+wishListMovie);

                wishListMovieRepository.save(wishListMovie);
            }
        }
        System.out.println(userRepository.count());
    }
}