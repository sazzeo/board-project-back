package com.jy.board.member.controller;


import com.jy.board.member.model.MemberDto;
import com.jy.board.member.service.MemberService;
import com.jy.board.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final String URI_PREFIX = "/api/board/auth";

    private final MemberService memberService;

    //회원가입
    @PostMapping(URI_PREFIX + "/join")
    public String addMember(@RequestBody MemberDto memberDto) {
        memberService.insertMember(memberDto);
        return "ok";
    }

    //로그인
    @PostMapping(URI_PREFIX + "/login")
    public String loginMember(@RequestBody MemberDto memberDto){
        String token = memberService.selectMember(memberDto);
        return token;
    }

    @PostMapping(URI_PREFIX + "/test")
    public String auth(@RequestHeader HttpHeaders httpHeaders) {
        System.out.println(httpHeaders.get("Authorization"));
        String token = (String) httpHeaders.get("Authorization").get(0);
        MemberDto memberDto = JwtTokenProvider.getMember(token);
        System.out.println(memberDto);
        return "ok";
    }

    @PostMapping(URI_PREFIX + "/test2")
    public String noAuth() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        MemberDto principal = (MemberDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        System.out.println("통과");
        return "ok";
    }


}
