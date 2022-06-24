package com.jy.board.posts.service;

import com.jy.board.posts.dao.PostsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    PostsService postsService;
    @Mock
    PostsRepository postsRepository;

    @BeforeEach
    public void init() {
        this.postsService = new PostsService(this.postsRepository);
    }

    @Test
    public void tt() {
        System.out.println("");
    }

}