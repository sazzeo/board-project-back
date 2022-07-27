package com.jy.board.posts.dao;

import com.jy.board.common.model.Pageable;
import com.jy.board.common.pagination.PageableResponse;
import com.jy.board.posts.model.PostsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestRepository {


    PageableResponse<PostsDto> selectTest(Pageable pageable);

    List<Map<String, String>> selectTest2(String query);

}
