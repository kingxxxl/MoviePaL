package com.example.moviepal.service;


import com.example.moviepal.controllers.UserController;
import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.NoUserFoundException;
import com.example.moviepal.exceptions.PasswordException;
import com.example.moviepal.model.User;
import com.example.moviepal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    @RequiredArgsConstructor
    public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

        private final UserRepository userRepository;

        public List<User> getAllUser(){
            return userRepository.findAll();
        }
        public void addUser(User user) {
            logger.info("Starting addUser from UserService");
            String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
            logger.info("created a hash password: "+ hashedPassword);
            try {
                user.setPassword(hashedPassword);
            }catch (Exception e){
                logger.warn("hash password was Not set to the user");
                throw new PasswordException("Something went wrong, try again");
            }

            logger.info("hash password was set to the user: " + user);
            user.setRole("User");
            userRepository.save(user);
        }
    public User getUserById(Integer id) {
            logger.info("starting getUserById: user id is "+id);
//        Optional<User> user = userRepository.findUserById(1);
//        Optional<User> user = userRepository.findUserByUsername("TEMP0");
                Optional<User> user = userRepository.findById(id);

        logger.info("starting getUserById: user is"+user.get());
        if (user.isPresent()){
            logger.info("user was found: "+user.get());
            return userRepository.findById(id).get();
        }else {
            logger.warn("Invalid id");
            throw new InvalidIdException("Invalid id");
        }
    }
    public Integer findUserIdByName(String name){
        logger.info("starting findUserIdByName: id is "+name);
        Optional<Integer> userid = userRepository.findUseridByUsername(name);
        if (userid.isPresent()){
            logger.info("userid was found: "+userid.get());
            return userid.get();
        }else {
            throw new NoUserFoundException("No user found with that name");
        }
    }
    
    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        }
    }
}