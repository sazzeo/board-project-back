package com.jy.board.member.controller;


import com.jy.board.member.model.MemberDto;
import com.jy.board.member.service.MemberService;
import com.jy.board.security.JwtTokenProvider;
import com.jy.board.security.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final String URI_PREFIX = "/api/board";

    private final MemberService memberService;

    //회원가입
    @PostMapping(URI_PREFIX + "/auth/join")
    public String addMember(@Valid @RequestBody MemberDto memberDto) {

        memberService.insertMember(memberDto);
        return "ok";
    }

    //로그인
    @PostMapping(URI_PREFIX + "/auth/login")
    public MemberDto loginMember(@RequestBody MemberDto memberDto) {
        MemberDto authMember = memberService.selectMember(memberDto);
        return authMember;
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
        MemberDto principal = (MemberDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        System.out.println("통과");
        return "ok";
    }

    @PutMapping(URI_PREFIX + "/user")
    public String modifyMember(@RequestBody MemberDto memberDto, @UserToken MemberDto authMember) {

        memberDto.setId(authMember.getId());
        memberService.updateMember(memberDto);
        return "ok";
    }

    /*아이디 중복체크*/
    @GetMapping(URI_PREFIX + "/auth/id-check")
    public Long findMemberExist(String id) {
        return memberService.selectMemberCount(id);
    }


}
