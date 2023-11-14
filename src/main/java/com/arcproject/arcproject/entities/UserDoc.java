package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="users")
public class UserDoc {
    @Id
    private String id;
    private String email;

    @Field("first_name")
    private String firstName;

    @Field("ip_address")
    private String ipAddress;


    public UserDoc(){
        
    }

    public UserDoc(String email){
        this.email = email;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getIpAddress(){
        return this.ipAddress;
    }

    public String getEmail(){
        return this.email;
    }
}
