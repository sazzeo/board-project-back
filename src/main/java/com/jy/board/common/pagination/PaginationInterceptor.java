package com.jy.board.common.pagination;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private ObjectMapper objectMapper;

    public PaginationInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {


        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        Object result = invocation.proceed();
        Pageable pageable = null;

        if(param.getClass().equals(Pageable.class) ) {
             pageable = (Pageable) param;
         }else if(param.getClass().equals(MapperMethod.ParamMap.class)) {
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap)param;
             pageable = (Pageable) paramMap.get("pageable");
        }else {

            return result;
        }


        if(pageable != null) {

            if(result.getClass().equals(ArrayList.class)) {
                List resultList = (ArrayList) result;

                if(resultList.size()>0) {
                    Long count = (Long) ((HashMap) resultList.get(0)).get("count");
                    if(count !=null) {

                        pageable.setTotalElements(count);
                    }

                    PageableResponse<Object> pageableResponse = new PageableResponse<>();
                    pageableResponse.setList(resultList);
                    pageableResponse.setPageable(pageable);

                    return pageableResponse.get();
                    //return pageableResponse;
                }
            }
        }




//
//        System.out.println("아이디:" + statement.getId());
//        System.out.println("쿼리 스트링" + statement.getBoundSql(param).getSql());
//
//        System.out.println("getResource: " + statement.getKeyProperties());
//
//        List<Object> res = (ArrayList<Object>) invocation.proceed();
//
//        System.out.println(">>>>>>>>>>>>>>>>"); //result Type알아 올 수 있음.
//        System.out.println(res); //result Type알아 올 수 있음.
//        System.out.println(">>>>>>>>>>>>>>>>"); //result Type알아 올 수 있음.

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

        return result;  //sql문 실행
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
