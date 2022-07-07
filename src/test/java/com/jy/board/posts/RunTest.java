package com.jy.board.posts;

import com.jy.board.security.PermitUrl;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class RunTest {

    @Test
    public void urlTest() {

        String[] str = PermitUrl.GET.getUrls();

        System.out.println(Arrays.toString(str));

    }

}
