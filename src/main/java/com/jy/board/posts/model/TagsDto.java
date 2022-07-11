package com.jy.board.posts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Alias("tagsDto")
public class TagsDto {
    private Long tagSeq;
    private Long postsSeq;
    private String tagName;
    private LocalDateTime  regDate;
    private int count;

}
