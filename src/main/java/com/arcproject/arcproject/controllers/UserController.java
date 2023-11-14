package com.arcproject.arcproject.controllers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get-by-email/{email}")
    public UserDoc sendDataToBrowser(@PathVariable String email) {
        UserDoc user = userService.getUserByEmail(email);

        if(user != null){
            return user;
        } else {
            return null;
        }
    }
}