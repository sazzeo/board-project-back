package com.jy.board.blog;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.BlogDto;
import com.jy.board.blog.model.CategoryDto;
import com.jy.board.config.MybatisConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MybatisConfig.class) //config 파일 import
public class BlogTest {


    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private ObjectMapper objectMapper;
    @BeforeEach
    public void before() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void insertTest() {
        BlogDto blogDto = new BlogDto();
        blogDto.setTitle("제목");
        blogDto.setSubTitle("제목2");
        blogDto.setUrl("url");
        blogDto.setId("아이디");

        blogRepository.insertBlog(blogDto);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setBlogSeq(100001L);
        categoryDto.setSort(1);
        categoryDto.setTitle("카테고리명");

        categoryRepository.insertCategory(categoryDto);

    }

    @Test
    public void selectCategory2() throws JsonProcessingException {
        Long blogSeq = 100007L;

        List<CategoryDto> categoryDtoList = categoryRepository.selectCategories("id8");
        String json = objectMapper.writeValueAsString(categoryDtoList);

        System.out.println(json);
    }
}
