package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="comments")
public class CommentDoc {
    @Id
    private String Id;

    @Field("author_uuid")
    private String authorId;

    @Field("story_uuid")
    private String storyId;

    private String comment;

    public CommentDoc(){

    }

    public CommentDoc(String authorId){
        this.authorId = authorId;
    }

    public String getStoryId(){
        return this.storyId;
    }

    public String getComment(){
        return this.comment;
    }

}
