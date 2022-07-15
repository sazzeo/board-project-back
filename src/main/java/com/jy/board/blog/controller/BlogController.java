package com.jy.board.blog.controller;

import com.jy.board.blog.model.BlogDto;
import com.jy.board.blog.model.MemberBlogDto;
import com.jy.board.blog.service.BlogService;
import com.jy.board.member.model.MemberDto;
import com.jy.board.security.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;
    private final String URI_PREFIX = "/api/board";


    //블로그 box정보
    @GetMapping(URI_PREFIX + "/auth/{url}/blog-profile")
    public Map<String, Object> findProfileBoxInfo(@PathVariable String url) {
       return blogService.selectProfileBoxInfo(url);
    }

    //유저 + 블로그 정보
    @GetMapping(URI_PREFIX + "/user/blog-profile")
    public MemberBlogDto findUserBlogProfile(@UserToken MemberDto memberDto) {
       return blogService.selectUserBlogProfile(memberDto);
    }

    @PutMapping(URI_PREFIX + "/blog")
    public int updateMember(@RequestBody BlogDto blogDto , @UserToken MemberDto memberDto) {
        blogDto.setId(memberDto.getId());
        return blogService.updateBlog(blogDto);

    }


}
