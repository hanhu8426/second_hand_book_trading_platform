package com.groupwork.secondhandbookstore;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SecondHandBookStoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJWT(){
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("name","hwj");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheiam")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .compact();

        System.out.println(jwt);
    }
    @Test
    public void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaHdqIiwiaWQiOjEsImV4cCI6MTY4MzQ0ODU1MH0.z9cNl1Omz2fBAp5lqr0Ju2KneCvSL-CMqVZfqBRgDcQ")
                .getBody();
        System.out.println(claims);
    }

}
