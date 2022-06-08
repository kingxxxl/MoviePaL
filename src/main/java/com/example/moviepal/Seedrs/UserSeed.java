package com.example.moviepal.Seedrs;

import com.example.moviepal.model.User;
import com.example.moviepal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeed implements CommandLineRunner {
    private final UserRepository userRepository;
    public UserSeed(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {
        if (userRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                String randomRate = String.valueOf((int) (Math.floor(Math.random() * 5) + 1));
                Integer randomDuration = (int) (Math.floor(Math.random() * 60) + 60);
                int randomGenre = (int) (Math.floor(Math.random() * 3) + 1);
                String genre ="comedy";
                switch (randomGenre){
                    case 1:
                    genre  = "Admin";
                    break;
                    case 2:
                    genre  = "User";
                    break;
                    case 3:
                        genre  = "comedy";
                        break;
                }

                String randomDate = String.valueOf((int) (Math.floor(Math.random() * 30) + 1990));
                User user = new User();
                user.setId(i);
                user.setUsername("TEMP");
                user.setPassword("Temp");
                userRepository.save(user);
            }
        }
        System.out.println(userRepository.count());
    }
}