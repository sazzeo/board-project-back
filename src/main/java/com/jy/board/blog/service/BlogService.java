package com.jy.board.blog.service;

import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;


}
