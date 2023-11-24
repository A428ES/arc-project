package com.arcproject.arcproject.authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.interfaces.UserInterface;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInterface userInterface;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDoc user = userInterface.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}