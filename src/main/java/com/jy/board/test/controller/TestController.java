package com.jy.board.test.controller;


import com.jy.board.test.dao.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/test")
    public String test() {
        System.out.println( testRepository.selectTest());
        return "ok";
    }


}
