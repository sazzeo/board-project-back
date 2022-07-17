package com.jy.board.blog.dao;

import com.jy.board.blog.model.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    int insertCategory(CategoryDto categoryDto);

    List<CategoryDto> selectCategory(Long blogSeq);

}
