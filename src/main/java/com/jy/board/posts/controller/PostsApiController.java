package com.jy.board.posts.controller;


import com.jy.board.common.pagination.PageDefault;
import com.jy.board.common.pagination.Pageable;
import com.jy.board.common.util.CustomResponseEntity;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import com.jy.board.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final String URI_PREFIX = "/api/board";

    //private final ObjectMapper mapper;


    private final PostsService postsService;

    private final CustomResponseEntity responseEntity;

    //게시글 목록 조회
    @GetMapping(URI_PREFIX+"/posts")
    public ResponseEntity<List<PostsDto>> findPosts(@PageDefault Pageable pageable) {
        //Pageable pageable = new Pageable();
        System.out.println(pageable);
        return responseEntity.success(postsService.selectPosts(pageable) , HttpStatus.OK);

    }

    //게시글 단건 조회
    @GetMapping(URI_PREFIX + "/posts/{postsSeq}")
    public PostsDto findPost(@PathVariable Long postsSeq, SpringDataWebProperties.Pageable pageable) {

        return postsService.selectPostBySeq(postsSeq);
    }


    //tags 목록조회
    @GetMapping(URI_PREFIX + "/tags/{postsSeq}")
    public List<String> findTags(@PathVariable Long postsSeq) {
        return postsService.selectTagListBySeq(postsSeq);
    }


    @PostMapping(URI_PREFIX+"/posts")
    public String addPosts(@Valid @RequestBody PostsDto postsDto) {
        postsService.insertPost(postsDto);
        return "ok";
    }

    //tags 전체 리스트 조회 ( top 3)
    @GetMapping(URI_PREFIX+"/tags")
    public List<TagsDto> findTagsOrderByTop(Integer size) {
        return postsService.selectTagsOrderByTop(size);
    }




}
