package com.jy.board.posts;


import com.jy.board.common.pagination.Pageable;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PostsTest {


    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    @Test
    public void selectPostsTest() {
        Pageable pageable = new Pageable();
        System.out.println(postsRepository.selectPosts(pageable));


    }


    @Test
    public void serviceTest() {
      //   System.out.println(postsService.selectPosts());
     //   System.out.println(postsRepository.test());
    }


    @Test
    public void selectPost() {
        Long seq = 100022L;
        System.out.println(postsRepository.selectPost(seq));


    }
}
