package com.arcproject.arcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arcproject.arcproject.interfaces.CommentInterface;

@Service
public class CommentService {
    private final CommentInterface commentInterface;

    @Autowired
    public CommentService(CommentInterface commentInterface){
        this.commentInterface = commentInterface;
    }

    public CommentInterface returnService(){
        return commentInterface;
    }
}
