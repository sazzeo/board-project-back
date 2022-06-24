package com.jy.board.common.pagination;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
//pageable 주입 클래스
public class PageableArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //PageDefault 어노테이션이 붙은 Pageable 일 경우에만 true
        boolean isPageDefaultAnnotation= parameter.getParameterAnnotation(PageDefault.class) !=null;
        boolean isPageableClass = Pageable.class.equals(parameter.getParameterType());

        return isPageDefaultAnnotation && isPageableClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        PageDefault pageDefault = parameter.getParameterAnnotation(PageDefault.class);

        System.out.println(">>>>>>>>>>>>인터셉터");
        int size =pageDefault.size();
        int page = pageDefault.page();
        if(webRequest.getParameter("size")!=null) {
             size = Integer.parseInt(webRequest.getParameter("size"));
        }
        if(webRequest.getParameter("page")!=null) {
             page = Integer.parseInt(webRequest.getParameter("page"));
        }
        //size파라미터와 page 파라미터가 없으면 기본값으로 설정

        //기본 ID값 부여해줘야함..
        String sort = pageDefault.sort();
        String direction = pageDefault.direction().getDirection();


        Pageable pageable = Pageable.builder()
                .page(page)
                .size(size)
                .sort(sort)
                .direction(direction).build();

        System.out.println(pageable);

        System.out.println(">>>>>>>>>>>>인터셉터");
        return pageable;
    }
}
