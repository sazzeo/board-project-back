package com.jy.board.security;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/index")
    public void index() {
    }

    @GetMapping("/hello")
    public void hello() {
    }

    @GetMapping("/my")
    public void my() {
    }

}
