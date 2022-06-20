package com.jy.board.posts.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class TagsDto {

    private Long tagSeq;
    private String tagName;
    private LocalDateTime  regDate;

}
