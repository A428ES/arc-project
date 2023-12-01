package com.arcproject.arcproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.arcproject.arcproject.entities.StoryDoc;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.interfaces.StoryInterface;
import com.arcproject.arcproject.interfaces.UserInterface;

@Service
public class StoryService {
    private final StoryInterface storyInterface;
    private final UserInterface userInterface;

    @Autowired
    public StoryService(StoryInterface storyInterface, UserInterface userInterface){
        this.storyInterface = storyInterface;
        this.userInterface = userInterface;
    }

    @Autowired

    public StoryInterface returnService(){
        return storyInterface;
    }

    public List<StoryDoc> findAll(){
        List<StoryDoc> stories = storyInterface.findAll();

        this.processStoriesAndUpdateAuthorNames(stories);

        return stories;
    }

    public List<StoryDoc> searchByTitleOrStory(String query){
        return storyInterface.searchByTitleOrStory(query);
    }

    public List<StoryDoc> findByAuthorId(String authorUuid){
        return storyInterface.searchByAuthorUuid(authorUuid);
    }

    public StoryDoc createStory(StoryDoc newStory){
        return storyInterface.save(newStory);
    }

    public void processStoriesAndUpdateAuthorNames(List<StoryDoc> stories) {
        stories.forEach(story -> {
            UserDoc user = userInterface.findByUuid(story.getAuthorUuid());

            if(user != null){
                story.setAuthorName(user.getfirst_name());
            } else {
                story.setAuthorName("USER DELETED");
            }
        });
    }
}
