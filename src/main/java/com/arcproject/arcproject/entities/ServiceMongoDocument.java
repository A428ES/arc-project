package com.arcproject.arcproject.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="story_app_db")
public class ServiceMongoDocument {
    @Id
    private String id;
}
