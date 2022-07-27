package com.jy.board.member.dao;


import com.jy.board.common.util.CamelMap;
import com.jy.board.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberRepository {
    int insertMember(MemberDto memberDto) ;

    MemberDto selectMember(String id);

    int updateMember(MemberDto memberDto);

    CamelMap<String, Long> selectMemberCount(String id);
}
