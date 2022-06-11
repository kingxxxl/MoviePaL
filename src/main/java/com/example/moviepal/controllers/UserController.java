package com.example.moviepal.controllers;

import com.example.moviepal.DTO.API;
import com.example.moviepal.model.User;
import com.example.moviepal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/logout-done")
    public ResponseEntity<API> logout(HttpServletRequest request) throws ServletException {
        SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
        ctxLogOut.logout(request,null,null);
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body(new API("Log ou was successful!",200));
    }

    @PostMapping("/register")
    public ResponseEntity<API> addUser(@RequestBody @Valid User user) {
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
