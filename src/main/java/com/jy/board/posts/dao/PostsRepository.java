package com.jy.board.posts.dao;


import com.jy.board.common.pagination.Pageable;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostsRepository {
    
    //전체 posts 조회
    List<PostsDto> selectPosts(Pageable pageable);

    int insertPost(PostsDto postsDto);

    int insertTag(TagsDto tagsDto);

    int updatePost(PostsDto postsDto);

    int updatePostsViews(Long postsSeq);

    //단건조회
    PostsDto selectPost(Long postsSeq);

    List<TagsDto> selectTagsBySeq(Long postsSeq);

    List<TagsDto> selectTagsBySeqList(@Param(value = "seqList") List<Long> seqList);

    List<TagsDto> selectTagsOrderByTop(int size);

    List<PostsDto> selectPostsByTagName(@Param(value = "tagName") String tagName , @Param(value = "pageable") Pageable pageable);

    int deletePostsBySeq(Long postsSeq);

    int deleteTagsByPostsSeq(Long postsSeq);


    int deleteTagsBySeq(Long tagSeq);
}
