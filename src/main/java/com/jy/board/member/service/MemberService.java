package com.jy.board.member.service;


import com.jy.board.member.dao.MemberRepository;
import com.jy.board.member.model.MemberDto;
import com.jy.board.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    @Transactional
    public int insertMember(MemberDto memberDto) {
        String password = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(password);
        return memberRepository.insertMember(memberDto);
    }

    @Transactional
    public MemberDto selectMember(MemberDto memberDto) {
        MemberDto selectMember = memberRepository.selectMember(memberDto.getId());


        if (selectMember == null) throw new UsernameNotFoundException("아이디가 틀렸습니다.");
        if (passwordEncoder.matches(memberDto.getPassword(), selectMember.getPassword())) {
            //토큰 파싱
            String newToken = tokenProvider.createToken(selectMember);
            System.out.println("새토큰: " + newToken);
            MemberDto tokenMember = JwtTokenProvider.getMember(newToken);
            System.out.println("파싱한 토큰: " +  tokenMember);
            return selectMember;
        } else {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 틀렸습니다.");
        }

    }


    //얘가 알아서 처리해 주는 부분..??
    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberDto selectMember = memberRepository.selectMember(username);
        if (selectMember == null) throw new UsernameNotFoundException("아이디가 틀렸습니다.");
        UserDetails userDetails = new User(username, selectMember.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return userDetails;
    }
}
