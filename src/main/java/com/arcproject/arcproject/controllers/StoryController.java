package com.arcproject.arcproject.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arcproject.arcproject.entities.StoryDoc;
import com.arcproject.arcproject.service.StoryService;
import com.arcproject.arcproject.util.CommonTools;

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

        return ResponseEntity.ok(CommonTools.convertResults(stories));
    }

    @PostMapping("/stories/search")
    public ResponseEntity<Map<String, Object>> sendSearchToBrowser(@RequestBody Map<String, String> payload){
        List<StoryDoc> stories = storyService.searchByTitleOrStory(payload.get("search"));
        
        return ResponseEntity.ok(CommonTools.convertResults(stories));
    }

    @PostMapping("/stories/mystories")
    public ResponseEntity<Map<String, Object>> userStoriesToBrowser(){
        return ResponseEntity.ok(CommonTools.convertResults(new Object[0]));
    }

    
}