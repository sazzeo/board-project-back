package com.jy.board.member.controller;


import com.jy.board.member.model.MemberDto;
import com.jy.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

        MemberDto loginMember = memberService.selectMember(memberDto);
        System.out.println(loginMember);

        return "ok";
    }


}
