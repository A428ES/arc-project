package com.arcproject.arcproject.entities;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="stories")
public class StoryDoc extends BaseDoc {

    private String title;
    private String story;
    private String author;
    private String author_uuid;


    public StoryDoc(){

    }

    public String getAuthor_uuid(){
        return this.author_uuid;
    }

    public String getTitle(){
        return this.title;
    }

    public String getStory(){
        return this.story;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthorName(String author){
        this.author = author;
    }

    public void setAuthor(String uuid){
        this.author_uuid = uuid;
    }

    public void setStory(String story){
        this.story = story;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
