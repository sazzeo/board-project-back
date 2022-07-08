package com.jy.board.security;

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
        String id = "id";
        String password = "password";


        UserDetails users = new CustomUser(id , password , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(users.getUsername() , users.getPassword() , users.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);  //=> 얘가 있냐 없냐에 따라서 시큐리티 권한이 달라짐

        filterChain.doFilter(request , response);
    }
}
