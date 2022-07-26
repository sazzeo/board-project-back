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
    List<PostsDto> selectPosts(Pageable pageable);

    List<PostsDto> selectPostsOfChildCategory(@Param("id") String id , @Param("title") String title);
    List<PostsDto> selectPostsOfParentCategory(@Param("id") String id , @Param("title") String title);

    List<PostsDto> selectAllPost(String id);

    List<PostsDto> selectPostsAsOption(@Param("pageable") Pageable pageable ,@Param("s") String s , @Param("o") String o );

    int insertPost(PostsDto postsDto);

    int insertTag(TagsDto tagsDto);

    int updatePost(PostsDto postsDto);

    int updatePostsViews(Long postsSeq);

    //단건조회
    PostsDto selectPost(Long postsSeq);

    List<TagsDto> selectTagsBySeq(Long postsSeq);

    List<TagsDto> selectTagsBySeqList(@Param(value = "seqList") List<Long> seqList);

    List<TagsDto> selectTagsOrderByTop(@Param("id") String id , @Param("tagMin") int tagMin ,@Param("tagCardinal") int tagCardinal);

    List<PostsDto> selectPostsByTagName(@Param(value = "tagName") String tagName , @Param(value = "pageable") Pageable pageable);

    int deletePostsBySeq(Long postsSeq);

    int deleteTagsByPostsSeq(Long postsSeq);


    int deleteTagsBySeqList(List<Long> tagSeqList);
}
