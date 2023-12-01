package com.arcproject.arcproject.controllers;
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.model.UserRegistration;
import com.arcproject.arcproject.service.UserService;
import com.arcproject.arcproject.util.CommonTools;
import com.arcproject.arcproject.util.SecurityUtil;

@RestController
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/check_logged_in")
    public ResponseEntity<Map<String, Object>> checkLoggedIn(Principal principal) {
        UserDoc user = userService.userWithStats(principal.getName());

        return ResponseEntity.ok(CommonTools.convertResults(user));
    }

    @PostMapping("/user/register")
    public ResponseEntity<Map<String, Object>> userRegistration(@RequestBody UserRegistration userRegistration){
        userService.newUser(userService.convertToUserDoc(userRegistration));

        return ResponseEntity.ok(CommonTools.convertResults("true"));
    }

    @PostMapping("/user/changepw")
    public ResponseEntity<Map<String, Object>> userRegistration(@RequestBody Map<String, String> payload, Principal principal){
        UserDoc workingUser = userService.getUserByEmail(principal.getName());

        if(SecurityUtil.checkPassword(payload.get("old_password"), workingUser.getPassword())){
            workingUser.setPassword(SecurityUtil.hashPassword(payload.get("new_password")));

            userService.newUser(workingUser);
        }

        return ResponseEntity.ok(CommonTools.convertResults("complete"));
    }
}