package com.example.kshitiz.server.utils;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public String extractEmail(String token) {
        // Minimal stub to allow local testing without a real JWT.
        // If the token already looks like an email, accept it; otherwise reject.
        if (token == null) {
            throw new RuntimeException("Invalid token");
        }
        String trimmed = token.trim();
        if (trimmed.contains("@")) {
            return trimmed;
        }
        throw new RuntimeException("Invalid token: email not found");
    }
}


