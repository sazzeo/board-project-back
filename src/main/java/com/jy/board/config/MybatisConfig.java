package com.jy.board.config;


import com.jy.board.common.pagination.PaginationInterceptor;
import com.jy.board.common.util.CamelMap;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        //페이지네이션 인터셉터 등록
        sessionFactory.setPlugins(new PaginationInterceptor());

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sql/*.xml");
        sessionFactory.setMapperLocations(res);
        sessionFactory.setTypeAliasesPackage("com.jy.board.**.*.model");
        sessionFactory.setTypeAliases(CamelMap.class);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);

        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }



}
