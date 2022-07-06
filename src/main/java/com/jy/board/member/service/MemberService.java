package com.jy.board.member.service;


import com.jy.board.member.dao.MemberRepository;
import com.jy.board.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public int insertMember(MemberDto memberDto) {
        return memberRepository.insertMember(memberDto);
    }
}
