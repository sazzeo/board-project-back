package com.jy.board.common.util;


import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

//join시 컬럼값 camel변환을 위한 클래스
public class CamelMap extends HashMap<String, Object> {

    @Override
    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }

}