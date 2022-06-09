package com.example.moviepal.controllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.model.User;
import com.example.moviepal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));

    }
    
    @PostMapping("/register")
    public ResponseEntity<API> addUser(@RequestBody @Valid User user, Errors errors) {
        logger.info("starting addUser from UserController, :" +user+" isErrors:  "+errors.hasErrors());

        if(errors.hasErrors()){
            logger.warn("user entered has errors");

            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new API(message,400));
        }
        logger.info("Starting addUser in UserController");
        logger.info("calling adduser to the user service: "+user);
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new API("New user was added!", 201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<API> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new API("User was deleted!", 201));

    }
}
