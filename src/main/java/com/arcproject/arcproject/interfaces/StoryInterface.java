package com.arcproject.arcproject.interfaces;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.arcproject.arcproject.entities.StoryDoc;


@Repository
public interface StoryInterface extends MongoRepository<StoryDoc, String> {
    @Query("{is_deleted:false}")
    List<StoryDoc> findAll();

    @Query("{$or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'story': { $regex: ?0, $options: 'i' } } ], is_deleted: false}")
    List<StoryDoc> searchByTitleOrStory(String query);

    @Query("{'author_uuid': ?0, is_deleted: false}")
    List<StoryDoc> searchByAuthorUuid(String author_uuid);

    @Query("{ 'uuid' : ?0, 'is_deleted': false}")
    StoryDoc findByUuid(String uuid);     
}

