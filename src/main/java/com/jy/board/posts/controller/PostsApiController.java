package com.jy.board.posts.controller;


import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final String URI_PREFIX = "/api/board";


    private final PostsService postsService;

    @GetMapping(URI_PREFIX+"/posts")
    public List<PostsDto> findPosts() {
        return postsService.selectPosts();

    }

    @GetMapping(URI_PREFIX + "/posts/{postsSeq}")
    public PostsDto findPost(@PathVariable Long postsSeq) {

        return postsService.selectPostBySeq(postsSeq);
    }

    @PostMapping(URI_PREFIX+"/posts")
    public String addPosts(PostsDto postsDto) {

        //postsRepository.insertPost(postsDto);

        return "ok";
    }


}
