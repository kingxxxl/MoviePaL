package com.example.moviepal.controllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.model.Movie;
import com.example.moviepal.model.User;
import com.example.moviepal.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));

    }
    
    @PostMapping
    public ResponseEntity<API> addUser(@RequestBody @Valid User user) throws IOException {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new API("New user was added!", 201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<API> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new API("User was deleted!", 201));

    }
}
