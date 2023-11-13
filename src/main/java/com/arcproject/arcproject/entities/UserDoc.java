package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserDoc {
    @Id
    private String id;
}
