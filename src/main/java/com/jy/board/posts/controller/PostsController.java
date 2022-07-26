package com.jy.board.posts.controller;


import com.jy.board.common.pagination.PageDefault;
import com.jy.board.common.pagination.Pageable;
import com.jy.board.common.util.CustomResponseEntity;
import com.jy.board.member.model.MemberDto;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import com.jy.board.posts.service.PostsService;
import com.jy.board.security.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final String URI_PREFIX = "/api/board";

    private final PostsService postsService;

    private final CustomResponseEntity customResponseEntity;

    //게시글 목록 조회
    @GetMapping(URI_PREFIX+"/posts")
    public ResponseEntity<Map<String , Object>> findPosts(@PageDefault() Pageable pageable, String s , String o) {

        Map<String,  Object> map = new HashMap<>();
        map.put("posts" , postsService.selectPosts(pageable, s, o));
        map.put("pageable" , pageable);
        return customResponseEntity.success(map, HttpStatus.OK);

    }

    //카테고리별 게시글 조회(공개글만 조회)
    @GetMapping({URI_PREFIX+"/auth/posts/{url}/category/{parentCategory}" ,
            URI_PREFIX+"/auth/posts/{url}/category/{parentCategory}/{childCategory}" ,
            URI_PREFIX+"/auth/posts/{url}/category"})
    public List<PostsDto> findPosts(@PathVariable String url , @PathVariable(required = false) String parentCategory
    , @PathVariable(required = false) String childCategory) {
        List<PostsDto> posts = postsService.selectPosts(url , parentCategory , childCategory);
        return posts;
    }


    //게시글 단건 조회 -로그인 안된 버전
    @GetMapping(URI_PREFIX + "/auth/posts/{postsSeq}")
    public PostsDto findPost(@PathVariable Long postsSeq , @UserToken MemberDto memberDto) {
        return postsService.selectPostBySeq(postsSeq);

    }

    //tags 목록조회
    @GetMapping(URI_PREFIX + "/tags/{postsSeq}")
    public List<String> findTags(@PathVariable Long postsSeq) {
        return postsService.selectTagListBySeq(postsSeq);
    }



    //게시글 등록
    @PostMapping(URI_PREFIX+"/posts")
    public String addPosts( @UserToken MemberDto memberDto, @RequestBody PostsDto posts ) {
        postsService.insertPost(memberDto , posts);
        return "ok";
    }

    @PutMapping(URI_PREFIX + "/posts/{postsSeq}")
    public String updatePost(@PathVariable Long postsSeq , @Valid  @RequestBody PostsDto postsDto) {
       postsService.updatePost(postsSeq , postsDto);
        return "ok";
    }
    
    
    //게시글 삭제
    @DeleteMapping(URI_PREFIX + "/posts/{postsSeq}")
    public String removePost(@PathVariable Long postsSeq , @UserToken MemberDto memberDto) {
        postsService.deletePosts(postsSeq , memberDto);
        return "ok";
    }


    //tags 전체 리스트 조회 ( top 3)
    @GetMapping(URI_PREFIX+"/auth/{id}/tag-box")
    public List<TagsDto> findTagsOrderByTop(@PathVariable String id) {
        return postsService.selectTagsOrderByTop(id);
    }



    //태그 이름으로 게시글 조회
    @GetMapping(URI_PREFIX + "/posts/tags/{tagName}")
    public ResponseEntity<List<PostsDto>> findPostsByTagName(@PathVariable String tagName , Pageable pageable) {
        Map<String,  Object> map = new HashMap<>();
        map.put("posts" , postsService.selectPostsByTagName(tagName , pageable));
        map.put("pageable" , pageable);
        return customResponseEntity.success(map, HttpStatus.OK);
    }




}
