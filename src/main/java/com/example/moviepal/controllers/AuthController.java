package com.example.moviepal.controllers;//package com.example.demosecurity.controllers;
//
//import com.example.bookmanagementsoftware.DTO.API;
//import com.example.bookmanagementsoftware.model.Auth;
//import com.example.bookmanagementsoftware.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//    @GetMapping
//    public ResponseEntity<List<Auth>> getAllAuth(){
//        return ResponseEntity.status(HttpStatus.OK).body(authService.getAllAuth());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Auth> getAuth(@PathVariable Integer id){
//        return ResponseEntity.status(HttpStatus.OK).body(authService.getAuth(id));
//
//    }
//
//    @PostMapping
//    public ResponseEntity<API> addAuth(@RequestBody @Valid Auth auth){
//        authService.addAuth(auth);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new API("New auth was added!", 201));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<API> deleteAuth(@PathVariable Integer id){
//        authService.deleteAuth(id);
//        return ResponseEntity.status(HttpStatus.OK).body(new API("Auth was deleted!", 201));
//
//    }
//}
