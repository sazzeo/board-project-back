package com.jy.board.blog.model;

import com.jy.board.member.model.MemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;
@NoArgsConstructor
@Data
@Alias("blogDto")
public class BlogDto {

    private Long blogSeq;
    private String id;
    private String title;
    private String subTitle;
    private String url;
    private String tagYn;
    private String tagMin;
    private String tagCardinal;
    private Date regDate;

    @Builder
    public BlogDto(String id, String title, String subTitle, String url) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.url = url;
    }
}
