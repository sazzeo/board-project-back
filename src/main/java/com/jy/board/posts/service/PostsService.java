package com.jy.board.posts.service;

import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {


    private final PostsRepository postsRepository;

    @Transactional
    public List<PostsDto> selectPosts() {
        List<PostsDto> postList = postsRepository.selectPosts();
        List<Long> seqList = postList.stream().map(PostsDto::getPostsSeq).collect(Collectors.toList());
        List<TagsDto> tagsDtos = postsRepository.selectTagsBySeqList(seqList);

        postList = postList.stream().map((post) -> {
            List<TagsDto> tags = tagsDtos.stream().filter(tag -> tag.getPostsSeq().equals(post.getPostsSeq()))
                    .collect(Collectors.toList());
            post.setTagList(tags);
            return post;
        }).collect(Collectors.toList());

        return postList;

        //마이바티스 컬렉션 태그 만들기!!

    }
    @Transactional
    public PostsDto selectPostBySeq(Long postsSeq) {
        postsRepository.updatePostsViews(postsSeq); //조회수 up
        return postsRepository.selectPost(postsSeq);
    }


    @Transactional
    public void insertPost(PostsDto postsDto, List<TagsDto> tagsDto) {

        postsRepository.insertPost(postsDto);
        System.out.println(">>>>>>>>>>>포스트 dto : " + postsDto.getPostsSeq());
        for (TagsDto dto : tagsDto) {
            dto.setPostsSeq(postsDto.getPostsSeq());
            postsRepository.insertTag(dto);
        }

    }


    public List<String> selectTagListBySeq(Long postsSeq) {
        List<String> TagNameList = postsRepository.selectTagsBySeq(postsSeq).stream().map(TagsDto::getTagName).collect(Collectors.toList());

        return TagNameList;
    }
}
