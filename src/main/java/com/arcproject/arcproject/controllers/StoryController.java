package com.arcproject.arcproject.controllers;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arcproject.arcproject.entities.StoryDoc;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.service.StoryService;
import com.arcproject.arcproject.service.UserService;
import com.arcproject.arcproject.util.CommonTools;

@RestController
@RequestMapping("/")
public class StoryController {
    private final StoryService storyService;
    private final UserService userService;

    @Autowired
    public StoryController(UserService userService, StoryService storyService){
        this.userService = userService;
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
    public ResponseEntity<Map<String, Object>> userStoriesToBrowser(Principal principal){

        List<StoryDoc> stories = storyService.findByAuthorId(userService.getUserByEmail(principal.getName()).getuuid());

        return ResponseEntity.ok(CommonTools.convertResults(stories));
    }

    @PostMapping("/stories/submit")
    public ResponseEntity<Map<String, Object>> newStoryIntoDb(@RequestBody Map<String, String> payload, Principal principal){
        
        UserDoc user = userService.getUserByEmail(principal.getName());
        
        StoryDoc newStory = new StoryDoc();
        newStory.setStory(payload.get("story"));
        newStory.setTitle(payload.get("title"));
        newStory.setAuthor(user.getuuid());
        storyService.createStory(newStory);

        return ResponseEntity.ok(CommonTools.convertResults("true"));
    }
}