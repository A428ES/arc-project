package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserDoc {
    @Id
    private String id;
    private String email;
    private String uuid;
    private String first_name;
    private String ip_address;


    public UserDoc(){
        
    }

    public UserDoc(String uuid){
        this.uuid = uuid;
    }

    public String getuuid(){
        return this.uuid;
    }

    public String getfirst_name(){
        return this.first_name;
    }

    public String getip_address(){
        return this.ip_address;
    }

    public String getEmail(){
        return this.email;
    }
}
