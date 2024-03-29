package com.jy.board.security;

import com.jy.board.member.model.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class CustomFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if((!StringUtils.isEmpty(token)) && JwtTokenProvider.validateToken(token)) {

            MemberDto memberDto = JwtTokenProvider.getMember(token);
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(memberDto , memberDto.getPassword() , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(userToken);  //=> 얘가 있냐 없냐에 따라서 시큐리티 권한이 달라짐
        }

        //UserDetails users = new CustomUser(m , password , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        filterChain.doFilter(request , response);
    }

}