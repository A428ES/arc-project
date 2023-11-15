package com.arcproject.arcproject.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arcproject.arcproject.entities.StoryDoc;
import com.arcproject.arcproject.service.StoryService;

@RestController
@RequestMapping("/")
public class StoryController {
    private final StoryService storyService;
    
    @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> sendDataToBrowser() {
        List<StoryDoc> stories = storyService.findAll();

        if(stories != null){
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("results", stories);
    
            return ResponseEntity.ok(jsonResponse);
        } else {
            return null;
        }
    }

    
}