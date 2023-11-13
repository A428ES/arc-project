package com.arcproject.arcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arcproject.arcproject.interfaces.StoryInterface;

@Service
public class StoryService {
    private final StoryInterface storyInterface;

    @Autowired
    public StoryService(StoryInterface storyInterface){
        this.storyInterface = storyInterface;
    }

    public StoryInterface returnService(){
        return storyInterface;
    }
}
