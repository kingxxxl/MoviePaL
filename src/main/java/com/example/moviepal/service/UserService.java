package com.example.moviepal.service;


import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.model.User;
import com.example.moviepal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository userRepository;

        public List<User> getAllUser(){
            return userRepository.findAll();
        }
        public void addUser(User user) {
            userRepository.save(user);
        }
    public User getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return userRepository.findById(id).get();
        }else {
            throw new InvalidIdException("Invalid id");
        }
    }
    
    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        }
    }
}