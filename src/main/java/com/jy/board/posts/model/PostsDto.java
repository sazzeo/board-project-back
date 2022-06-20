package com.jy.board.posts.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class PostsDto {

    private Long postsSeq;
    private String title;
    private String content;
    private String member;
    private Date regDate;
    private int views;


    @Builder
    public PostsDto(String title, String content, String member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
