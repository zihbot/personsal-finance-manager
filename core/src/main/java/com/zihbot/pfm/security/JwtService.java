package com.zihbot.pfm.security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    private final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public static final String TOKEN_PREFIX = "Bearer ";

    private Algorithm algorithm;

    public JwtService(@Value("${spring.datasource.password}") String jwtSecret) {
        algorithm = Algorithm.HMAC256(jwtSecret);
    }

    public String createToken(String username) {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() + 15*60*1000))
            .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            return JWT.require(algorithm)
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        } catch (Exception e) {
            logger.warn("Wrong token {}", token);
        }
        return null;
    }
}
