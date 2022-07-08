package com.jy.board.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtTokenProvider {

    private static final String JWT_SECRET = "secretKey";
    // 토큰 유효시간
    private static final long JWT_EXPIRATION_MS = 60*60*1000L; //1시간으로 지정




    //Authentication 객체 : 사용자 인증정보가 들어있음
    //사용자 인증객체를 받아서 jwt토큰 생성하기
    public static String createToken(Authentication authentication) {

        Date now = new Date();
        //인증시간
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject((String)authentication.getPrincipal())
                .setIssuedAt(new Date()) //현재 시간 기반으로 생성
                .setExpiration(expiryDate) //만료시간 셋팅
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact();

    }



    //토큰 전달받아서 유저정보 받기
    public static String getPrincipal(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }




}
