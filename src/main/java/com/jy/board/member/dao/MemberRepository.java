package com.jy.board.member.dao;


import com.jy.board.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    int insertMember(MemberDto memberDto) ;

    MemberDto selectMember(String id);

}
