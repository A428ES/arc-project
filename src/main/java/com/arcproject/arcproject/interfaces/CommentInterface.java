package com.arcproject.arcproject.interfaces;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arcproject.arcproject.entities.UserDoc;

@Repository
public interface CommentInterface extends MongoRepository<UserDoc, String> {
    
}
