package com.jy.board.common.util;


import org.apache.ibatis.type.Alias;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

//join시 컬럼값 camel변환을 위한 클래스
@Alias("camelMap")
public class CamelMap<S, O> extends HashMap<String, O> {

    @Override
    public O put(String key, O value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key),  value);
    }

}