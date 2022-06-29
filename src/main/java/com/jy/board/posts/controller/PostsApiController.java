package com.jy.board.posts.controller;


import com.jy.board.common.exception.CustomException;
import com.jy.board.common.exception.ExceptionCode;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final String URI_PREFIX = "/api/board";

    //private final ObjectMapper mapper;


    private final PostsService postsService;

    private final CustomResponseEntity responseEntity;

    //게시글 목록 조회
    @GetMapping(URI_PREFIX+"/posts")
    public ResponseEntity<Map<String , Object>> findPosts(@PageDefault Pageable pageable) {

        System.out.println("페이지:::::::::::::>>" + pageable);

        Map<String,  Object> map = new HashMap<>();
        map.put("posts" , postsService.selectPosts(pageable));
        map.put("pageable" , pageable);
        return responseEntity.success(map, HttpStatus.OK);

    }

    //게시글 단건 조회
    @GetMapping(URI_PREFIX + "/posts/{postsSeq}")
    public PostsDto findPost(@PathVariable Long postsSeq) {

        return postsService.selectPostBySeq(postsSeq);
    }


    //tags 목록조회
    @GetMapping(URI_PREFIX + "/tags/{postsSeq}")
    public List<String> findTags(@PathVariable Long postsSeq) {
        return postsService.selectTagListBySeq(postsSeq);
    }



    //게시글 등록
    @PostMapping(URI_PREFIX+"/posts")
    public String addPosts(@Valid @RequestBody PostsDto postsDto) {
        postsService.insertPost(postsDto);
        return "ok";
    }

    @PutMapping(URI_PREFIX + "/posts/{postsSeq}")
    public String updatePost(@PathVariable Long postsSeq , @RequestBody PostsDto postsDto) {
       postsService.updatePost(postsSeq , postsDto);
        return "ok";
    }
    
    
    //게시글 삭제
    @DeleteMapping(URI_PREFIX + "/posts/{postsSeq}")
    public String removePost(@PathVariable Long postsSeq) {
        postsService.deletePosts(postsSeq);
        return "ok";
    }


    //tags 전체 리스트 조회 ( top 3)
    @GetMapping(URI_PREFIX+"/tags")
    public List<TagsDto> findTagsOrderByTop(Integer size) {
        return postsService.selectTagsOrderByTop(size);
    }



    //태그 이름으로 게시글 조회
    @GetMapping(URI_PREFIX + "/posts/tags/{tagName}")
    public ResponseEntity<List<PostsDto>> findPostsByTagName(@PathVariable String tagName) {
        Pageable pageable = new Pageable();
        Map<String,  Object> map = new HashMap<>();
        map.put("posts" , postsService.selectPostsByTagName(tagName , pageable));
        map.put("pageable" , pageable);
        return responseEntity.success(map, HttpStatus.OK);
    }


}
