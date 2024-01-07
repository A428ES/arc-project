package com.arcproject.arcproject.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.arcproject.arcproject.authentication.CustomUserDetailsService;
import com.arcproject.arcproject.entities.UserDoc;
import com.arcproject.arcproject.model.AuthenticationRequest;
import com.arcproject.arcproject.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
public class TestAuthenticationController {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void checkOnline_ShouldReturnOnlineStatus() throws Exception {
        mockMvc.perform(get("/checkonline"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("online")));
    }

    @Test
    public void createAuthenticationToken_WithValidCredentials_ShouldReturnToken() throws Exception {
        // Arrange
        String email = "user@example.com";
        String password = "password";
        AuthenticationRequest request = new AuthenticationRequest(email, password);
        UserDetails mockUserDetails = mock(UserDetails.class);
        UserDoc mockUserDoc = new UserDoc();  // Set necessary fields for UserDoc
        String mockToken = "mockToken";

        when(userDetailsService.loadUserByUsername(email)).thenReturn(mockUserDetails);
        when(jwtTokenUtil.generateToken(mockUserDetails)).thenReturn(mockToken);
        when(userDetailsService.getUserLoginResponse(email, mockToken)).thenReturn(mockUserDoc);

        // Act & Assert
        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(notNullValue())));
    }

    @Test
    public void createAuthenticationToken_WithInvalidCredentials_ShouldThrowException() throws Exception {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongPassword";
        AuthenticationRequest request = new AuthenticationRequest(email, password);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .thenThrow(new BadCredentialsException("Incorrect username or password"));

        // Act & Assert
        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
