package com.arcproject.arcproject.interfaces;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.arcproject.arcproject.entities.ServiceMongoDocument;

public interface MongoInterface extends MongoRepository<ServiceMongoDocument, String> {
    
}
