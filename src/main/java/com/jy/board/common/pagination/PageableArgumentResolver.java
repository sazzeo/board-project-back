package com.jy.board.common.pagination;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;

@Component
public class PageableArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isPageDefaultAnnotation= parameter.getParameterAnnotation(PageDefault.class) !=null;
        boolean isPageableClass = Pageable.class.equals(parameter.getParameterType());

        return isPageDefaultAnnotation && isPageableClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        PageDefault pageDefault = parameter.getParameterAnnotation(PageDefault.class);

        int size =pageDefault.size();
        int page = pageDefault.page();
        if(webRequest.getParameter("size")!=null) {
            size = Integer.parseInt(webRequest.getParameter("size"));
        }
        if(webRequest.getParameter("page")!=null) {
            page = Integer.parseInt(webRequest.getParameter("page"));
        }

        String sort = pageDefault.sort();
        String direction = pageDefault.direction().getDirection();


        Pageable pageable = Pageable.builder()
                .page(page)
                .size(size)
                .sort(sort)
                .direction(direction).build();
        return pageable;


    }
}
