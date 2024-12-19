package com.example.class_demo.controllers;

import com.example.class_demo.Service.PostService;
import com.example.class_demo.entities.User;
import com.example.class_demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;



    @PostMapping(path = "/addUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User savedUser = userRepo.save(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping(path = "/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return  ResponseEntity.ok().body((List<User>) userRepo.findAll());
    }

}

