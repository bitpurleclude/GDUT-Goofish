package org.gdutgoodfish.goodfish.util;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenUtils {
    private final Set<String> tokenBlacklist = new HashSet<>();
    public void invalidateToken(String token) {
        tokenBlacklist.add(token);
    }
    public boolean isTokenInvalidated(String token) {
        return tokenBlacklist.contains(token);
    }
}
