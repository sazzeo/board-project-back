package com.jy.board.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//AuthenticationEntryPoint: 인증 과정에서 예외가 발생한 경우 예외를 핸들링하는 인터페이스
//인증 실패 처리를 담당함
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;
    public CustomAuthenticationEntryPoint() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Map<String, Object> result = new HashMap<>();
        result.put("message", "Auth Exception");
        response.setStatus(401);
        response.getWriter().print(this.objectMapper.writeValueAsString(result));

    }


}
