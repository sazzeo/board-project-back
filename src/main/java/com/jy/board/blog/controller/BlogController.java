package com.jy.board.blog.controller;

import com.jy.board.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;
    private final String URI_PREFIX = "/api/board";


    @GetMapping(URI_PREFIX + "/auth/{url}/blog-profile")
    public Map<String, Object> findProfileBoxInfo(@PathVariable String url) {
       return blogService.selectProfileBoxInfo(url);
    }

}
