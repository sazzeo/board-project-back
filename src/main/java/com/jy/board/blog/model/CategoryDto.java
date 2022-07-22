package com.jy.board.blog.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Data
@Alias("categoryDto")
public class CategoryDto {

    private Long categorySeq;
    private Long BlogSeq;
    private String title;
    private int sort;

    private int totalCnt;

    private Date regDate;

    private Long upCategory;

    private List<CategoryDto> children;

    private boolean publicYn;

    @Builder
    public CategoryDto(Long blogSeq, String title, int sort , boolean publicYn) {
        BlogSeq = blogSeq;
        this.title = title;
        this.sort = sort;
        this.publicYn = publicYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDto)) return false;
        CategoryDto that = (CategoryDto) o;
        return getSort() == that.getSort()  && isPublicYn() == that.isPublicYn() && getCategorySeq().equals(that.getCategorySeq()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getUpCategory(), that.getUpCategory());
    }



    @Override
    public int hashCode() {
        return Objects.hash(getCategorySeq(), getTitle(), getSort(), getUpCategory(), isPublicYn());
    }
}
