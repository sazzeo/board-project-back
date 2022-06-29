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



        System.out.println(  testRepository.selectTest2("select * from tags"));

        
    }
    @Test
    public void  test2() {

        System.out.println("테스트코드");

        System.out.println(postsRepository.selectPosts(new Pageable()));

    }

    @Test void test3() {

        List<String> list = new ArrayList<>();
        boolean a = list instanceof ArrayList;
        System.out.println(a);


    }
}
