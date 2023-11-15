package com.arcproject.arcproject.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.CommentDoc;
import com.arcproject.arcproject.service.CommentService;
import com.arcproject.arcproject.util.CommonTools;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    
    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/display")
    public ResponseEntity<Map<String, Object>> commentsDisplay(@RequestBody Map<String, String> payload) {
        List<CommentDoc> comments = commentService.findByStoryUuid(payload.get("story_id"));
        Map<String, Object> jsonResponse = new HashMap<>();

        jsonResponse.put("results", comments);
        
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> commentsCount(@RequestParam("id") String story_uuid) {
        List<CommentDoc> comment = commentService.findByStoryUuid(story_uuid);
        String total = (comment != null) ? Integer.toString(comment.size()) : "0";

        return ResponseEntity.ok(CommonTools.convertResults(total));
    }
}