package com.jy.board.posts;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RunTest {

    @Test
    public void test() {

        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();

        a.add("a");
        a.add("b");
        a.add("c");

        b.add("c");

        b.add("d");

        a.stream().forEach((aa)->{
            System.out.println(aa);
        });


        //지울 친구들
        List<String >  c= a.stream().filter( e -> {
            return !b.contains(e);
        }).collect(Collectors.toList());
        //추가할 친구들
        b.removeAll(a);

        System.out.println(c);
        System.out.println(b);
//        a.removeAll(b);
//        System.out.println(b);
        //System.out.println( a.retainAll(b)); a && b
        //System.out.println(a);  //겹치는것만
        //System.out.println(b);





    }

}
