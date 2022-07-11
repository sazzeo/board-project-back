package com.jy.board.security;

import com.jy.board.member.model.MemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
    public class JwtTokenProvider {

//        private static final String JWT_SECRET = "secretKey";
    private static final String JWT_SECRET = "asdfasdfdsafadsfdsafdasf";
    // 토큰 유효시간
    private static final long JWT_EXPIRATION_MS = 60*60*1000L; //1시간으로 지정


    //Authentication 객체 : 사용자 인증정보가 들어있음
    //사용자 인증객체를 받아서 jwt토큰 생성하기
    public static String createToken(MemberDto memberDto) {

        Date now = new Date();
        //인증시간
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(memberDto.getId())
                .claim("password" , memberDto.getPassword()) //토큰 속성 추가
                .claim("name" , memberDto.getName())
                .setIssuedAt(new Date()) //현재 시간 기반으로 생성
                .setExpiration(expiryDate) //만료시간 셋팅
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact(); //jwt토큰(간략화) 만들기
    }


    //토큰 전달받아서 유저정보 받기 => 유저 정보 리턴
    public static MemberDto getMember(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        MemberDto member = new MemberDto();
        member.setId(claims.getSubject());
        member.setPassword((String) claims.get("password"));
        member.setName((String) claims.get("name"));
        return member;
    }

    //토큰이 유효한지 아닌지 확인하기
    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return false;
    }




}
