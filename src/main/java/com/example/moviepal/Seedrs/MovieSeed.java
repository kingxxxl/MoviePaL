//package com.example.moviepal.Seedrs;
//
//import com.example.movieratingsoftware.Model.Movie;
//import com.example.movieratingsoftware.Repository.MovieRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MovieSeed implements CommandLineRunner {
//
//    private final MovieRepository movieRepository;
//
//    public MovieSeed(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadMovieData();
//    }
//    private void loadMovieData() {
//        if (movieRepository.count() == 0) {
//            System.out.println("I was here1");
//            for (int i = 200; i < 220; i++) {
//                String randomRate = String.valueOf((int) (Math.floor(Math.random() * 5) + 1));
//                Integer randomDuration = (int) (Math.floor(Math.random() * 60) + 60);
//                int randomGenre = (int) (Math.floor(Math.random() * 3) + 1);
//                String genre ="comedy";
//                switch (randomGenre){
//                    case 1:
//                    genre  = "action";
//                    break;
//                    case 2:
//                    genre  = "drama";
//                    break;
//                    case 3:
//                        genre  = "comedy";
//                        break;
//                }
//
//                String randomDate = String.valueOf((int) (Math.floor(Math.random() * 30) + 1990));
//                Movie movie = new Movie(i,"Home Alone"+i, genre,randomRate, randomDuration, randomDate, null);
//                movieRepository.save(movie);
//            }
//            Movie movie1 = new Movie(300,"Home Alone"+300, "comedy","2", 60, "2000", null);
//            Movie movie2 = new Movie(301,"Home Alone"+301, "comedy","2", 60, "2000", null);
//            movieRepository.save(movie1);
////            movieRepository.save(movie2);
//
//        }
//        System.out.println(movieRepository.count());
//    }
//}