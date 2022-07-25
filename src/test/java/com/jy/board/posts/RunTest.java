package com.jy.board.posts;

import com.jy.board.blog.model.CategoryDto;
import com.jy.board.security.PermitUrl;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class RunTest {

    @Test
    public void urlTest() {

        String[] str = PermitUrl.GET.getUrls();

        System.out.println(Arrays.toString(str));

    }

    @Test
    public void equalsTest() {
        CategoryDto categoryDto1 = new CategoryDto();
        CategoryDto categoryDto2 = new CategoryDto();

        categoryDto1.setCategorySeq(1L);
        categoryDto2.setCategorySeq(1L);
        categoryDto1.setTitle("제목");
        categoryDto2.setTitle("제목");
        categoryDto1.setSort(1);
        categoryDto2.setSort(1);
        categoryDto1.setTotalCnt(0);
        categoryDto2.setTotalCnt(1);

        System.out.println(categoryDto2.equals(categoryDto1));

    }


}
