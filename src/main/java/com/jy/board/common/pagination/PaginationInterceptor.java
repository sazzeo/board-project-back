package com.jy.board.common.pagination;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


//마이바티스용 인터셉터

//args : 이 파라미터들이 invocation.getArgs에 순서대로 들어감
// MappedStatement
// Object : 파라미터가 들어있음
// RowBounds
//ResultHandler
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class PaginationInterceptor  implements Interceptor {

    public PaginationInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {



//        MappedStatement mappedStatement = (MappedStatement)  invocation.getArgs()[0];
//        Object param = invocation.getArgs()[1];
//        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) param;
//        Map<String , Object> newParamMap = new HashMap<>();
//        if(param.getClass().equals(MapperMethod.ParamMap.class)) {
//
//            for (Object o : paramMap.keySet()) {
//                String key = (String) o ;
//                if(key.contains("param")) {
//                    newParamMap.put( key , paramMap.get(o));
//                }
//            }
//        }
//
//        System.out.println(newParamMap);
//
//        //여기--------------------
//        System.out.println( (mappedStatement.getBoundSql(param).getSql()));

        return invocation.proceed();  //sql문 실행
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

}
