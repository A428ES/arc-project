package com.arcproject.arcproject.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("/send-data")
    public String sendDataToBrowser() {
        // Your logic to generate or retrieve data
        String data = "Hello, this data is sent to the browser!";
        return data;
    }
}