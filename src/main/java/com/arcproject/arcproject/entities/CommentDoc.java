package com.arcproject.arcproject.entities;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
public class CommentDoc extends BaseDoc {

    private String author_uuid;
    private String story_uuid;
    private String comment;
    private String author;

    public CommentDoc(){

    }

    public CommentDoc(String author_uuid){
        this.author_uuid = author_uuid;
    }

    public String getauthor_uuid(){
        return this.author_uuid;
    }

    public String getcomment_uuid(){
        return this.getuuid();
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

    public void setAuthor(String uuid){
        this.author_uuid = uuid;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setStoryUuid(String storyUuid){
        this.story_uuid = storyUuid;
    }
}
