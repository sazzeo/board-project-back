package com.jy.board.posts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
public class TagsDto {

    private Long tagSeq;
    private Long postsSeq;
    private String tagName;
    private LocalDateTime  regDate;

}
