package com.ecnu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    /*
    *  生成JWT令牌
    * */
    @Test
    public void testGenerateJwt() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id","1");
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"ithema") //指定加密算法、密钥
                .addClaims(dataMap) //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) //设置过期时间
                .compact();

        System.out.println(jwt);
    }

    /*
    * 解析令牌
    * */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzQyMjE3NDIxfQ.aJo_1gJgrHaH9cuS-AWJtSgvdoQpPgnE7tSVkIx1OTA";
        Claims claims = Jwts.parser()
                .setSigningKey("ithema") //指定密钥
                .parseClaimsJws(token) //解析令牌
                .getBody(); //获得自定义信息
        System.out.println(claims);
    }
}
