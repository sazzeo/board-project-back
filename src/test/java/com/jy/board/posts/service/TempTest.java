package com.jy.board.posts.service;


import com.jy.board.common.pagination.Pageable;
import com.jy.board.common.pagination.PageableResponse;
import com.jy.board.config.MybatisConfig;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.dao.TestRepository;
import com.jy.board.posts.model.PostsDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MybatisConfig.class) //config 파일 import
public class TempTest {


    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void  test() {

        System.out.println("테스트코드");

      //  인터셉터 전까지는 쟤를 따라가다가? 매핑될때 애가 헤까닥 돌아버림이군

        PageableResponse<PostsDto> post =   testRepository.selectTest(new Pageable());
        System.out.println(post.get());
        System.out.println(post.getPageable());
        
    }
    @Test
    public void  test2() {

        System.out.println("테스트코드");

        System.out.println(postsRepository.selectPosts(new Pageable()));

    }
}
