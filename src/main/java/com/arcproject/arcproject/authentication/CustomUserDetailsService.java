package com.arcproject.arcproject.authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.service.UserService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDoc user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found " + email + " if nothing else, email empty");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}