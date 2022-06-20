package com.jy.board.posts.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.PostsSaveDto;
import com.jy.board.posts.model.TagsDto;
import com.jy.board.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final String URI_PREFIX = "/api/board";

    //private final ObjectMapper mapper;


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
    public String addPosts(@RequestBody PostsSaveDto postsSaveDto) {
        System.out.println(postsSaveDto);

        PostsDto postsDto = postsSaveDto.getPostsDto();
        List<TagsDto> tagsDto = postsSaveDto.getTagsDto();

        postsService.insertPost(postsDto , tagsDto);

        return "ok";
    }




}
