package com.jy.board.blog.dao;


import com.jy.board.blog.model.BlogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BlogRepository {

    int insertBlog(BlogDto blogDto);


    //사용자 프로필 박스
    Map<String, Object> selectProfileBoxInfo(String url);

}
