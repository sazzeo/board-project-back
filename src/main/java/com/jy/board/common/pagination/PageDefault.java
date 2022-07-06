package com.jy.board.common.pagination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageDefault {

    int size() default 10;
    int page() default 0;
    String sort() default ""; //sort할 컬럼값

    SortDirection direction () default SortDirection.ASC;

}