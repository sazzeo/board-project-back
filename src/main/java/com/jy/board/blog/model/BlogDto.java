package com.jy.board.blog.model;

import com.jy.board.member.model.MemberDto;

import java.util.Date;

public class BlogDto {

    private Long blogSeq;
    private MemberDto memberDto;
    private String title;
    private String subTitle;
    private String url;
    private String tagYn;
    private String tagMin;
    private String tagCardinal;
    private Date regDate;

}
