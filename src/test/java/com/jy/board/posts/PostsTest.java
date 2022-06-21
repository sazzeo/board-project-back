package com.jy.board.posts;


import com.jy.board.config.MybatisConfig;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.service.PostsService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
public class PostsTest {


    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    @Test
    public void selectPostsTest() {

        System.out.println(postsRepository.selectPosts());


    }

    @Test
    public void insertTest() {
        PostsDto postsDto = PostsDto.builder()
                .title("제목")
                .content("내용")
                .member("작성자")
                .build();

    }

    @Test
    public void serviceTest() {
      //   System.out.println(postsService.selectPosts());
        System.out.println(postsRepository.test());
    }
}
