package com.jy.board.member;

import com.jy.board.member.model.MemberDto;
import com.jy.board.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void insertTest() {
        MemberDto memberDto = new MemberDto();

        memberDto.setId("id2");
        memberDto.setPassword("password");
        memberDto.setName("임지영");
        memberDto.setEmail("zo4870@naver.com");
        memberDto.setPhone("01088329612");
        memberService.insertMember(memberDto);

        System.out.println("인서트완료");

    }
}
