package com.jy.board.blog.dao;


import com.jy.board.blog.model.BlogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogRepository {

    int insertBlog(BlogDto blogDto);
}
