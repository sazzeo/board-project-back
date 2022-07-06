package com.jy.board.member;


import com.jy.board.config.MybatisConfig;
import com.jy.board.member.dao.MemberRepository;
import com.jy.board.member.model.MemberDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MybatisConfig.class) //config 파일 import
public class MemberRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        MemberDto memberDto = new MemberDto();

        memberDto.setId("id2");
        memberDto.setPassword("password");
        memberDto.setName("임지영");
        memberDto.setEmail("zo4870@naver.com");
        memberDto.setPhone("01088329612");
//        memberDto.setAddr("주소");
//        memberDto.setPostCode(0);
//        memberDto.setDetailAddr("주소2");
        memberRepository.insertMember(memberDto);

        System.out.println("인서트완료");

    }
}
