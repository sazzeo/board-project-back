package com.jy.board.posts.service;

import com.jy.board.common.exception.CustomException;
import com.jy.board.common.exception.ExceptionCode;
import com.jy.board.common.pagination.Pageable;
import com.jy.board.member.model.MemberDto;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;


    //게시글 리스트 조회
    @Transactional
    public List<PostsDto> selectPosts(Pageable pageable) {
        List<PostsDto> res = postsRepository.selectPosts(pageable);
        return res;
    }



    /** 주석 메소드 만들때 달기
     * 설명 :
     * 작성일 : 2022. 07. 01.
     * @author : jy.lim
     * @param pageable
     * @param s
     * @param o
     * @return
     */
  @Transactional
    public List<PostsDto> selectPosts(Pageable pageable ,String s,String o) {
      List<PostsDto> res = null;
        if(s !=null) {
            res = postsRepository.selectPostsAsOption(pageable , s , o );
        }else {
            res = postsRepository.selectPosts(pageable);
        }
        return res;
    }


    //게시글 단건조회

    /**
     * @return
     */
    @Transactional
    public PostsDto selectPostBySeq() {
        return selectPostBySeq(null);
    }

    @Transactional
    public PostsDto selectPostBySeq(Long postsSeq) {
        PostsDto postsDto = postsRepository.selectPost(postsSeq);

        if(postsDto == null) throw new CustomException(ExceptionCode.PATH_ERROR);
        postsRepository.updatePostsViews(postsSeq); //조회수 up
        return postsDto;
    }


    @Transactional
    public void insertPost(MemberDto memberDto , PostsDto postsDto  ) {

        postsDto.setId(memberDto.getId());
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
        List<Long> tagSeqList = tags.stream()
                .filter(tag-> !newTags.contains(tag))
                .map(TagsDto::getTagSeq)
                .collect(Collectors.toList());

        if(tagSeqList.size()>0) {
            postsRepository.deleteTagsBySeqList(tagSeqList);
        }

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
        return postsRepository.selectPostsByTagName(tagName ,pageable);
    }


    public void deletePosts(Long postsSeq) {
        postsRepository.deleteTagsByPostsSeq(postsSeq);
        postsRepository.deletePostsBySeq(postsSeq);
    }
}
