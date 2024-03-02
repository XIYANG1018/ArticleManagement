package org.example;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class JwtTest {

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Bob");
        // generate jwt codes
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12)) // keep this not expire by adding time
                .sign(Algorithm.HMAC256("itheima")); // secrete key

        System.out.println(token);
    }


    @Test
    public void testParse() {
        // simulate token passed by user
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IkJvYiJ9LCJleHAiOjE3MDkzNzQ0ODB9.Lixe5SLcO-U4V4tJFHMW_nODYOUuBAkZ1TofBowG5zY"; // the token just created

        // build verifier
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();

        // verify token，generate a parsed jwt object
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));


    }

    // token: Header, PayLoad, Signature
    // use: java-jwt, APT, parse

}
