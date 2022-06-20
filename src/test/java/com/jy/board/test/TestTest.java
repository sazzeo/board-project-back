package com.jy.board.test;

import com.jy.board.config.MybatisConfig;
import com.jy.board.test.dao.TestRepository;
import com.jy.board.test.model.TestDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MybatisConfig.class) //config 파일 import
public class TestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTest.class);
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        TestRepository bean = applicationContext.getBean(TestRepository.class);

        LOGGER.info("Check : {}", bean);

        List<TestDto> test =testRepository.selectTest();
        System.out.println(test);

    }

}
