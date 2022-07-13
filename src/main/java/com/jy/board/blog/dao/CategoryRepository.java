package com.jy.board.blog.dao;

import com.jy.board.blog.model.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryRepository {

    int insertCategory(CategoryDto categoryDto);
}
