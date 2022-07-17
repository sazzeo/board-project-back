package com.jy.board.blog.service;

import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.BlogDto;
import com.jy.board.blog.model.CategoryDto;
import com.jy.board.blog.model.MemberBlogDto;
import com.jy.board.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Map<String, Object> selectProfileBoxInfo(String url) {
        return blogRepository.selectProfileBoxInfo(url);
    }

    @Transactional
    public MemberBlogDto selectUserBlogProfile(MemberDto memberDto) {
        String id = memberDto.getId();
        return blogRepository.selectProfile(id);
    }


    @Transactional
    public int updateBlog(BlogDto blogDto) {
        return blogRepository.updateBlog(blogDto);
    }

    @Transactional
    public CategoryDto selectCategory(Long blogSeq) {
        List<CategoryDto> categories = categoryRepository.selectCategory(blogSeq);

        categories.stream().filter(categoryDto -> categoryDto.getUpCategory() == null).map(CategoryDto->{
            return new CategoryDto();
        }).collect(Collectors.toList());

        System.out.println(categories);

        return null;
    }

}
