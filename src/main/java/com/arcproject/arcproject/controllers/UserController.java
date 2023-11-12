package com.arcproject.arcproject.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String sendDataToBrowser() {
        // Your logic to generate or retrieve data
        String data = "a login attempt!";
        return data;
    }
}