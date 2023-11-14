package com.arcproject.arcproject.interfaces;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arcproject.arcproject.entities.StoryDoc;


@Repository
public interface StoryInterface extends MongoRepository<StoryDoc, String> {
    List<StoryDoc> findAll();
}

