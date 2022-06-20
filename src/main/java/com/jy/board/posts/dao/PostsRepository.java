package com.jy.board.posts.dao;


import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsRepository {

    List<PostsDto> selectPosts() ;

    int insertPost(PostsDto postsDto);

    int insertTag(TagsDto tagsDto);

    int updatePostsViews(Long postsSeq);

    PostsDto selectPost(Long postsSeq);

}
