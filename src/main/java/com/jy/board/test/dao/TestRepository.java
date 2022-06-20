package com.jy.board.test.dao;


import com.jy.board.test.model.TestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TestRepository {

    List<TestDto> selectTest() ;

}
