package com.arcproject.arcproject.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.service.UserService;
import com.arcproject.arcproject.util.CommonTools;

@RestController
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get-by-email/{email}")
    public UserDoc sendDataToBrowser(@PathVariable String email) {
        UserDoc user = userService.getUserByEmail(email);

        if(user != null){
            return user;
        } else {
            return null;
        }
    }

    @GetMapping("/user/check_logged_in")
    public ResponseEntity<Map<String, Object>> checkLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
        // Get user details from authentication object
        UserDoc user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(CommonTools.convertResults(user));
    }

    return ResponseEntity.ok(CommonTools.convertResults("No user is currently authenticated"));
}
}