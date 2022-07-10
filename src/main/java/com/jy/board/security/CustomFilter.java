package com.jy.board.security;

import com.jy.board.member.model.MemberDto;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class CustomFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        MemberDto memberDto = JwtTokenProvider.getMember(token);


        System.out.println("여기실행");
        //UserDetails users = new CustomUser(m , password , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(memberDto , memberDto.getPassword() , null);

        SecurityContextHolder.getContext().setAuthentication(userToken);  //=> 얘가 있냐 없냐에 따라서 시큐리티 권한이 달라짐

        filterChain.doFilter(request , response);
    }
}
