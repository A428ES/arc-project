package com.arcproject.arcproject.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="users")
public class UserDoc extends BaseDoc {

    private String email;
    private String first_name;
    private String last_name;
    private byte[] password;


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

    public String getEmail(){
        return this.email;
    }
}
