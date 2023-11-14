package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
public class CommentDoc {
    @Id
    private String Id;
    private String author_uuid;
    private String story_uuid;
    private String comment;
    private Double created_timestamp_ms;

    public CommentDoc(){

    }

    public CommentDoc(String author_uuid){
        this.author_uuid = author_uuid;
    }

    public String getauthor_uuid(){
        return this.author_uuid;
    }

    public String getstory_uuid(){
        return this.story_uuid;
    }

    public String getContent(){
        return this.comment;
    }

    public String getAuthor(){
        return this.author_uuid;
    }

    public String getDate(){
        return this.created_timestamp_ms.toString();
    }

}
