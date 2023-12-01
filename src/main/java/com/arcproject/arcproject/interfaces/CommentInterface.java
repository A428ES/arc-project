package com.arcproject.arcproject.interfaces;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.arcproject.arcproject.entities.CommentDoc;

@Repository
public interface CommentInterface extends MongoRepository<CommentDoc, String> {
    @Query("{ 'author_uuid' : ?0 , 'isDeleted': false}")
    List<CommentDoc> findByAuthorId(String author_uuid);

    @Query("{ 'story_uuid' : ?0, 'isDeleted': false}")
    List<CommentDoc> findByStoryUuid(String story_uuid);

    @Query("{ 'uuid' : ?0, 'isDeleted': false}")
    CommentDoc findByUuId(String uuid);
}
