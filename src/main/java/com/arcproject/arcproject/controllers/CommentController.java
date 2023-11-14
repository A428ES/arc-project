package com.arcproject.arcproject.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.CommentDoc;
import com.arcproject.arcproject.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    
    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/mycomments/{authorId}")
    public List<CommentDoc> sendDataToBrowser(@PathVariable String authorId) {
        List<CommentDoc> comment = commentService.findCommentsByAuthorId(authorId);

        if(comment != null){
            return comment;
        } else {
            return null;
        }
    }
}