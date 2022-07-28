package com.jy.board.member.service;


import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.BlogDto;
import com.jy.board.blog.model.CategoryDto;
import com.jy.board.common.exception.CustomException;
import com.jy.board.common.exception.ExceptionCode;
import com.jy.board.member.dao.MemberRepository;
import com.jy.board.member.model.MemberDto;
import com.jy.board.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;


    /**
     * 설명 : 멤버 가입 + 블로그 생성 + 카테고리 생성 
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param memberDto
     */
    @Transactional
    public void insertMember(MemberDto memberDto) {

        Long memberCount = memberRepository.selectMemberCount(memberDto.getId()).get("idCount");
        if(memberCount > 0) {
            throw new CustomException(ExceptionCode.DUPLICATE_ID); //아이디 중복 에러
        }
        String password = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(password);
        //멤버추가
        memberRepository.insertMember(memberDto);

        String memberId = memberDto.getId();
        BlogDto blogDto = BlogDto.builder()
                .id(memberId)
                .title(memberId + "의 블로그")
                .subTitle(memberId + "의 블로그 입니다.")
                .url(memberId).build();
        blogRepository.insertBlog(blogDto);

        CategoryDto categoryDto = CategoryDto.builder()
                .blogSeq(blogDto.getBlogSeq())
                .title("게시판")
                .sort(1)
                .publicYn(true)
                .build();
        categoryRepository.insertCategory(categoryDto);
    }


    /**
     * 설명 : 로그인 + 토큰 발행
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param memberDto
     * @return
     */
    @Transactional
    public MemberDto selectMember(MemberDto memberDto) {
        MemberDto selectMember = memberRepository.selectMember(memberDto.getId());

        if (selectMember == null) throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        if (!passwordEncoder.matches(memberDto.getPassword(), selectMember.getPassword())) {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        //토큰 파싱
        String token = tokenProvider.createToken(selectMember);
        selectMember.setAuthToken(token);
        return selectMember;
    }


    /**
     * 설명 : 안씀 
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param username the username identifying the user whose data is required.
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberDto selectMember = memberRepository.selectMember(username);
        if (selectMember == null) throw new UsernameNotFoundException("아이디가 틀렸습니다.");
        UserDetails userDetails = new User(username, selectMember.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return userDetails;
    }

    /**
     * 설명 : 유저 정보 업데이트
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param memberDto
     * @return
     */
    @Transactional
    public int updateMember(MemberDto memberDto) {

        if(memberDto.getPassword() != null) {
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        }
        return memberRepository.updateMember(memberDto);
    }

    /**
     * 설명 : 유저 아이디 중복확인용
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param id
     * @return
     */
    public Long selectMemberCount(String id) {
        Long idCount = memberRepository.selectMemberCount(id).get("idCount");
        return idCount;
    }

}
