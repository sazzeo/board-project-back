package com.jy.board.posts.dao;


import com.jy.board.common.pagination.Pageable;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostsRepository {
    
    //전체 posts 조회
    List<PostsDto> selectPosts(Pageable pageable) ;

    int insertPost(PostsDto postsDto);

    int insertTag(TagsDto tagsDto);

    int updatePostsViews(Long postsSeq);

    PostsDto selectPost(Long postsSeq);

    List<TagsDto> selectTagsBySeq(Long postsSeq);

    List<TagsDto> selectTagsBySeqList(@Param(value = "seqList") List<Long> seqList);

    List<TagsDto> selectTagsOrderByTop(int size);


}
