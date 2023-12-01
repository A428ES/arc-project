package com.arcproject.arcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.interfaces.UserInterface;
import com.arcproject.arcproject.model.UserRegistration;
import com.arcproject.arcproject.util.SecurityUtil;

@Service
public class UserService {
    private final UserInterface userInterface;

    @Autowired
    public UserService(UserInterface userInterface){
        this.userInterface = userInterface;
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
}
