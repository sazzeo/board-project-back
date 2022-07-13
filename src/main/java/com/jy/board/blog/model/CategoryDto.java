package com.jy.board.blog.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@NoArgsConstructor
@Data
@Alias("categoryDto")
public class CategoryDto {

    private Long categorySeq;
    private Long BlogSeq;
    private String title;
    private int sort;
    private Long upCategory;
    private int totalCnt;
    private Date regDate;

    @Builder
    public CategoryDto(Long blogSeq, String title, int sort) {
        BlogSeq = blogSeq;
        this.title = title;
        this.sort = sort;
    }
}
