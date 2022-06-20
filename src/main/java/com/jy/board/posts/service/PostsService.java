package com.jy.board.posts.service;

import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {


    private final PostsRepository postsRepository;

    @Transactional
    public List<PostsDto> selectPosts() {

        return postsRepository.selectPosts();

    }

    @Transactional
    public PostsDto selectPostBySeq(Long postsSeq) {
        postsRepository.updatePostsViews(postsSeq); //조회수 up
        return postsRepository.selectPost(postsSeq);
    }


    @Transactional
    public void insertPost(PostsDto postsDto , List<TagsDto> tagsDto) {

        postsRepository.insertPost(postsDto);
        System.out.println(">>>>>>>>>>>포스트 dto : " + postsDto.getPostsSeq());
        for (TagsDto dto : tagsDto) {
            dto.setPostsSeq(postsDto.getPostsSeq());
            postsRepository.insertTag(dto);
        }

    }


}
