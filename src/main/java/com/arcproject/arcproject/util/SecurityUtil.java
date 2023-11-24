package com.arcproject.arcproject.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class SecurityUtil{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static byte[] hashPassword(String plainPassword) {
        return encoder.encode(plainPassword).getBytes();
    }

    public static Boolean checkPassword(String plainPassword, String encrytpedPassword){
        return encoder.matches(plainPassword, encrytpedPassword);
    }
}