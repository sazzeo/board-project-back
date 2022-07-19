package com.jy.board.config;

import com.jy.board.security.handler.CustomAuthenticationEntryPoint;
import com.jy.board.security.CustomFilter;
import com.jy.board.security.PermitUrl;
import com.jy.board.security.handler.JwtAuthenticationExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final String[] permitUrl = {"/", "/api/board/join"};

    //등록한 필터는 무조건 돈다
    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, PermitUrl.POST.getUrls()).permitAll()
                .antMatchers(PermitUrl.GET.getUrls()).permitAll() //
                .antMatchers("/api/board/auth/**").permitAll()
                .anyRequest().authenticated() //나머지는 인증 필요함
//                .anyRequest().permitAll()
                .and()
                .sessionManagement() //세션 끄기
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAuthenticationExceptionHandler())
                .and()
                .anonymous().disable()
                .cors() //rest api 기반이므로 cors 켜기
                .and()
                .csrf()//rest api기반이므로 csrf도 끄기
                .disable()
                .formLogin()//시큐리티 기본 form 로그인
                .disable()
                .httpBasic()
                .disable(); //시큐리티 기본 httpBasic 로그인 (prompt)
    }

}
