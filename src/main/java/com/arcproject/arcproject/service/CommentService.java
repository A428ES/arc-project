package com.arcproject.arcproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcproject.arcproject.entities.CommentDoc;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.interfaces.CommentInterface;
import com.arcproject.arcproject.interfaces.UserInterface;

@Service
public class CommentService {
    private final CommentInterface commentInterface;
    private final UserInterface userInterface;

    @Autowired
    public CommentService(CommentInterface commentInterface, UserInterface userInterface){
        this.commentInterface = commentInterface;
        this.userInterface = userInterface;
    }

    public CommentInterface returnService(){
        return commentInterface;
    }

    public List<CommentDoc> findCommentsByAuthorId(String author_uuid){
        return commentInterface.findByAuthorId(author_uuid);
    }

    public List<CommentDoc> findByStoryUuid(String story_uuid){
        List<CommentDoc> comments = commentInterface.findByStoryUuid(story_uuid);

        this.processCommentsAndUpdateAuthorNames(comments);

        return comments;
    }

    public void addComment(CommentDoc newComment){
        commentInterface.save(newComment);
    }

    public List<CommentDoc> findByAuthorId(String author_uuid){
        List<CommentDoc> comments = commentInterface.findByAuthorId(author_uuid);

        this.processCommentsAndUpdateAuthorNames(comments);

        return comments;
    }

    public void processCommentsAndUpdateAuthorNames(List<CommentDoc> comments) {
        comments.forEach(comment -> {
            UserDoc user = userInterface.findByUuid(comment.getAuthorUuid());
            String authorName = user == null ? "USER DELETED" : user.getfirst_name();
            
            comment.setAuthorName(authorName);
        });
    }
}
