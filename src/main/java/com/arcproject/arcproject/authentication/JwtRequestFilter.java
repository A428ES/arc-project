package com.arcproject.arcproject.authentication;

import org.springframework.web.filter.OncePerRequestFilter;

import com.arcproject.arcproject.util.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private JwtUtil jwtUtil;


    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, java.io.IOException {
    
        final String authorizationHeader = request.getHeader("Authorization");
    
        String email = null;
        String jwt = null;
    
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractUsername(jwt);
        }
    
        if (email != null && (SecurityContextHolder.getContext().getAuthentication() == null || 
            SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))) {
    
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
    
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    
        chain.doFilter(request, response);
    }
}