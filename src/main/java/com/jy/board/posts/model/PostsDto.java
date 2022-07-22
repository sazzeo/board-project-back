package com.jy.board.posts.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Alias("postsDto")
public class PostsDto {

    private Long postsSeq;
    private Long categorySeq;
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
    private String id;
    private int views;
    private int likeCnt;
    private List<TagsDto> tagList;
    private Date regDate;


    @Builder
    public PostsDto(String title, String content, String id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

}