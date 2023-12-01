package com.arcproject.arcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.interfaces.CommentInterface;
import com.arcproject.arcproject.interfaces.StoryInterface;
import com.arcproject.arcproject.interfaces.UserInterface;
import com.arcproject.arcproject.model.UserRegistration;
import com.arcproject.arcproject.util.CommonTools;
import com.arcproject.arcproject.util.SecurityUtil;

@Service
public class UserService {
    private final UserInterface userInterface;
    private final StoryInterface storyInterface;
    private final CommentInterface commentInterface;

    @Autowired
    public UserService(UserInterface userInterface, CommentInterface commentInterface, StoryInterface storyInterface){
        this.userInterface = userInterface;
        this.storyInterface = storyInterface;
        this.commentInterface = commentInterface;
    }

    public UserDoc getUserByEmail(String userEmail) {
        return userInterface.findByEmail(userEmail);
    }

    public UserDoc findByUuid(String uuid){
        return userInterface.findByUuid(uuid);
    }

    public UserDoc convertToUserDoc(UserRegistration userRegistration) {
        UserDoc userDoc = new UserDoc();

        userDoc.setFirstName(userRegistration.getFirstName());
        userDoc.setLastName(userRegistration.getLastName());
        userDoc.setEmail(userRegistration.getEmail());
        userDoc.setPassword(SecurityUtil.hashPassword(userRegistration.getPassword()));

        return userDoc;
    }

    public void newUser(UserDoc newUser){
        userInterface.save(newUser);
    }

    public UserDoc userWithStats(String userEmail){
        UserDoc userDoc = userInterface.findByEmail(userEmail);

        userDoc.setStoryCount(storyInterface.searchByAuthorUuid(userDoc.getuuid()).size());
        userDoc.setCommentCount(commentInterface.findByAuthorId(userDoc.getuuid()).size());
        userDoc.setCreated(CommonTools.convertStamp(userDoc.getCreatedTimestampMs()));

        return userDoc;
    }
}
