package com.arcproject.arcproject.controllers;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.CommentDoc;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.service.CommentService;
import com.arcproject.arcproject.service.UserService;
import com.arcproject.arcproject.util.CommonTools;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    
    @Autowired
    public CommentController(CommentService commentService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/display")
    public ResponseEntity<Map<String, Object>> commentsDisplay(@RequestBody Map<String, String> payload) {
        List<CommentDoc> comments = commentService.findByStoryUuid(payload.get("story_id"));

        return ResponseEntity.ok(CommonTools.convertResults(comments));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> commentsCount(@RequestParam("id") String story_uuid) {
        List<CommentDoc> comment = commentService.findByStoryUuid(story_uuid);
        String total = (comment != null) ? Integer.toString(comment.size()) : "0";

        return ResponseEntity.ok(CommonTools.convertResults(total));
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> newStoryIntoDb(@RequestBody Map<String, String> payload, Principal principal){
        
        UserDoc user = userService.getUserByEmail(principal.getName());
        
        CommentDoc newComment = new CommentDoc();
        newComment.setComment(payload.get("comment"));
        newComment.setStoryUuid(payload.get("story"));
        newComment.setAuthor(user.getuuid());
        commentService.addComment(newComment);

        return ResponseEntity.ok(CommonTools.convertResults("true"));
    }

    @PostMapping("/mycomments")
    public ResponseEntity<Map<String, Object>> userCommentsToBrowser(Principal principal){

        List<CommentDoc> comments = commentService.findByAuthorId(userService.getUserByEmail(principal.getName()).getuuid());

        return ResponseEntity.ok(CommonTools.convertResults(comments));
    }

    
    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody Map<String, String> payload, Principal principal){

        UserDoc user = userService.getUserByEmail(principal.getName());
        CommentDoc commentToDelete = commentService.findByUuid(payload.get("uuid"));
        String outcome = "No change processed";

        if(commentToDelete != null){
            if(commentToDelete.getAuthorUuid().equals(user.getuuid())){
                commentToDelete.setDeleted(true);
commentService.updateComment(commentToDelete);                                                                                                              
                outcome = commentToDelete.getuuid();
            } else {
                outcome = "This comment does not belong to authenticated user";
            }
        }

        return ResponseEntity.ok(CommonTools.convertResults(outcome));
    }

}