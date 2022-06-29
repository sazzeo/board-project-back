package com.jy.board.common.pagination;

import lombok.Builder;
import lombok.Data;

@Data
public class Pageable {

    //몇개씩 들고올건지?
    private int size;

    //현재 페이지
    private int page;

    //전체 페이지수
    private int totalPages;

    //전체 요소 수
    private Long totalElements;

    private int start;
    private int end;

    private String direction;
    private String sort;  //sort 할 키



    //페이지 기본값 설정
    public Pageable() {
        this.size = 10;
        this.page = 0;
    }


    @Builder
    public Pageable(int size, int page, String direction, String sort) {
        this.size = size;
        this.page = page;
        this.direction = direction;
        this.sort = sort;
    }

    public int getStart() {
        return page*size + 1;
    }

    public int getEnd() {
        return (page+1)*size;
    }

    public int getTotalPages() {
        if(totalElements!=null)  return (int)(totalElements/size) +1;
        return 0;
    }
}
