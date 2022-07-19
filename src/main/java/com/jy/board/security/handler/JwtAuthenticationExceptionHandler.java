package com.jy.board.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//인증에는 성공했으나 권한이 맞지 않는 경우 403에러
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {


        System.out.println(exception);
        System.out.println("잡힘");

    }

}