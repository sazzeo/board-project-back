package com.jy.board.posts.model;

import com.jy.board.common.pagination.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Alias("postsDto")
public class PostsDto {

    @Id
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