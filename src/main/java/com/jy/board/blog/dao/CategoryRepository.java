package com.jy.board.blog.dao;

import com.jy.board.blog.model.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    int insertCategory(CategoryDto categoryDto);

    List<CategoryDto> selectCategories(String id);

    List<CategoryDto> selectCategoriesForUpdate(String id);

    List<CategoryDto> selectCategoriesForInsertPosts(String id);
    int updateCategory(CategoryDto categoryDto) ;
    int updateCategoryTotalCnt(Long categorySeq);
    int deleteCategory(List<Long> categorySeq);




}
