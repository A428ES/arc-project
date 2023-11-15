package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.arcproject.arcproject.util.CommonTools;

@Document(collection="comments")
public class CommentDoc {
    @Id
    private String id;
    private String uuid;
    private String author_uuid;
    private String story_uuid;
    private String comment;
    private String author;
    private Double created_timestamp_ms;

    public CommentDoc(){

    }

    public CommentDoc(String author_uuid){
        this.author_uuid = author_uuid;
    }

    public String getauthor_uuid(){
        return this.author_uuid;
    }

    public String getcomment_uuid(){
        return this.uuid;
    }

    public String getstory_uuid(){
        return this.story_uuid;
    }

    public String getContent(){
        return this.comment;
    }

    public String getAuthorUuid(){
        return this.author_uuid;
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
