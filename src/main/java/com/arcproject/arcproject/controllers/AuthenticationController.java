package com.arcproject.arcproject.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.arcproject.arcproject.authentication.CustomUserDetailsService;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.model.AuthenticationRequest;
import com.arcproject.arcproject.util.CommonTools;
import com.arcproject.arcproject.util.JwtUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping(value = "/checkonline")
    public ResponseEntity<?> checkOnline(){
        return ResponseEntity.ok(CommonTools.convertResults("online"));
    }

    @GetMapping(value = "/user/login")
    public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        UserDoc userDetail = userDetailsService.getUserLoginResponse(userDetails.getUsername(), jwtTokenUtil.generateToken(userDetails));

        return ResponseEntity.ok(CommonTools.convertResults(userDetail));
    }
}