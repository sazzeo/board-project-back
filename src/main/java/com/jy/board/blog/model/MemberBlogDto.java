package com.jy.board.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("memberBlogDto")
@NoArgsConstructor
@Data
public class MemberBlogDto{

    /*유저정보*/
    private String id;
    private String name;
    private String email;
    private String phone;
    private int postCode;
    private String addr;
    private String detailAddr;

    /*블로그 정보*/
    private String title;
    private String subTitle;
    private String url;
    private Boolean tagYn;
    private String tagMin;
    private String tagCardinal;


}
