package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="stories")
public class StoryDoc {
    @Id
    private String id;
    private String title;
    private String story;
    private String date;
    private String author;
    private String author_uuid;


    public StoryDoc(){

    }

    public String getAuthorUuid(){
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

    // public String getAuthor(){
    //     UserDoc user = userService.findByUuid(this.author_uuid);

    //     if(user != null){
    //         return user.getfirst_name();
    //     } else {
    //         return "not found";
    //     }
        
    // }

    public String getDate(){
        this.date = "yesterday";

        return this.date;
    }
}
