package com.jy.board.blog;

import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void categoryTest() {
        Long blogSeq = 100007L;

        //List<CategoryDto> categoryDtoList = categoryRepository.selectCategories(blogSeq);
        //System.out.println(categoryDtoList);
    }
}
