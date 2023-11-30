package com.arcproject.arcproject.model;

public class AuthenticationResponse {
    private final String access;

    public AuthenticationResponse(String jwtToken) {
        this.access = jwtToken;
    }

    public String getAccess() {
        return access;
    }
}