package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="users")
public class UserDoc {
    @Id
    private String id;
    private String email;
    private String uuid;
    private String first_name;
    private String last_name;
    private String ip_address;
    private byte[] password;


    public UserDoc(){
    }

    @JsonIgnore
    public String getPassword(){
        
        return new String(password);
    }

    public UserDoc(String uuid){
        this.uuid = uuid;
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
