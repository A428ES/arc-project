package com.arcproject.arcproject.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="users")
public class UserDoc extends BaseDoc {

    private String email;
    private String first_name;
    private String last_name;
    private byte[] password;
    private Integer story_count;
    private Integer comment_count;
    private String created;
    private String access;


    public UserDoc(){
    }

    @JsonIgnore
    public String getPassword(){
        
        return new String(password);
    }

    public String getlastname(){
        return this.last_name;
    }

    public String getfirstname(){
        return this.first_name;
    }

    public String getUsername(){
        return this.email;
    }

    public String getfirst_name(){
        return this.first_name;
    }

   public String getEmail(){
        return this.email;
    }

    public Integer getstory_count(){
        return this.story_count;
    }

    public Integer getcomment_count(){
        return this.comment_count;
    }

    public String getcreated(){
        return this.created;
    }

    public void setFirstName(String firstName){
        this.first_name = firstName;
    }

    public void setLastName(String lastName){
        this.last_name = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(byte[] password){
        this.password = password;
    }

    public void setStoryCount(Integer storyCount){
        this.story_count = storyCount;
    }

    public void setCommentCount(Integer commentCount){
        this.comment_count = commentCount;
    }

    public void setCreated(String created){
        this.created = created;
    }

    public String getAccess(){
        return this.access;
    }

    public void setAccess(String access){
        this.access = access;
    }

 
}
