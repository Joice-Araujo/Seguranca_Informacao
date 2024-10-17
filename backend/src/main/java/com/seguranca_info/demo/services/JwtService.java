package com.seguranca_info.demo.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


@Service
public class JwtService {
    private final String SECRET_KEY = "9fafdae7920449ee4407fe456d101ba753a9e1c0a9851858a5ecfdfec1d0b4f5";

    private Claims extractAllClaims(String token) {
        JwtParser jwt = Jwts
                .parser()
                .verifyWith(getSignKey())
                .build();
        return jwt.parseSignedClaims(token).getPayload();
    }

    public boolean isValid(String token, UserDetails user) {
        String email = extractUsername(token);
        return (email.equals(user.getUsername()) && !this.isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = this.extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(UserDetails user) {
        String token = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
                .signWith(getSignKey())
                .compact();
        return token;
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
