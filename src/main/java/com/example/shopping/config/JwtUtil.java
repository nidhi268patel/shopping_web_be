package com.example.shopping.config;

import java.util.Date;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	private final String SECRET = "my-super-secret-key-my-super-secret-key";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long EXPIRATION = 86400000;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        return !isExpired(token);
    }

    private boolean isExpired(String token) {
        Date exp = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return exp.before(new Date());
    }
}
