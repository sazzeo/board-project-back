package com.jy.board.posts.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class PostsDto {

    private Long postsSeq;
    @NotEmpty
    private String title;
    private String content;
    private String member;
    private Date regDate;
    private int views;

    private List<TagsDto> tagList;


    @Builder
    public PostsDto(String title, String content, String member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
