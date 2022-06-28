package com.jy.board.posts.service;

import com.jy.board.common.exception.CustomException;
import com.jy.board.common.exception.ExceptionCode;
import com.jy.board.common.pagination.Pageable;
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
    public List<PostsDto> selectPosts(Pageable pageable) {

        return postsRepository.selectPosts(pageable );
    }

    @Transactional
    public PostsDto selectPostBySeq(Long postsSeq) {
        postsRepository.updatePostsViews(postsSeq); //조회수 up
        return postsRepository.selectPost(postsSeq);
    }


    @Transactional
    public void insertPost(PostsDto postsDto) {

        postsRepository.insertPost(postsDto);

        for (TagsDto dto : postsDto.getTagList()) {
            dto.setPostsSeq(postsDto.getPostsSeq());
            postsRepository.insertTag(dto);
        }

    }

    @Transactional
    public void updatePost(Long postsSeq , PostsDto postsDto) {
        postsDto.setPostsSeq(postsSeq);

        //원래 태그
        List<TagsDto> tags = postsRepository.selectPost(postsSeq).getTagList();
        //수정된 태그
        List<TagsDto> newTags = postsDto.getTagList();

        //없어진 태그 삭제하기
        tags.stream().filter(tag-> !newTags.contains(tag))
                .forEach(removeTag->{
                    postsRepository.deleteTagsBySeq(removeTag.getTagSeq());
                });
        //새로 생긴 태그 추가하기
        newTags.removeAll(tags);
        newTags.forEach(insertTag-> {
            insertTag.setPostsSeq(postsSeq);
            postsRepository.insertTag(insertTag);
        });

        postsRepository.updatePost(postsDto);
    }

    @Transactional
    public List<String> selectTagListBySeq(Long postsSeq) {
        List<String> TagNameList = postsRepository.selectTagsBySeq(postsSeq).stream().map(TagsDto::getTagName).collect(Collectors.toList());

        return TagNameList;
    }

    @Transactional
    public List<TagsDto> selectTagsOrderByTop(Integer size) {
        size = size == null ? 3 : size;
        return postsRepository.selectTagsOrderByTop(size);
    }


    @Transactional
    public List<PostsDto> selectPostsByTagName(String tagName , Pageable pageable ) {
        return postsRepository.selectPostsByTagName(tagName ,pageable );
    }


    public void deletePosts(Long postsSeq) {
        postsRepository.deleteTagsByPostsSeq(postsSeq);
        postsRepository.deletePostsBySeq(postsSeq);
    }
}
