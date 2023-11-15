package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.arcproject.arcproject.util.CommonTools;



@Document(collection="stories")
public class StoryDoc {
    @Id
    private String id;
    private String uuid;
    private String title;
    private String story;
    private String author;
    private String author_uuid;
    private Double created_timestamp_ms;


    public StoryDoc(){

    }

    public String getuuid(){
        return this.uuid;
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


    public String getDate(){
        return CommonTools.convertStamp(this.created_timestamp_ms);
    }
}
