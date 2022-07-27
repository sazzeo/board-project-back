package com.jy.board.common.pagination;

import com.jy.board.common.model.Pageable;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class PageableResponse<T> extends ArrayList<T> {

    //object 정보 담는 곳
    private List<T> list;

    //페이징 정보 담는 곳
    private Pageable pageable;


    public PageableResponse() {
        list = new ArrayList<>();
    }


    public List<T> get() {
        return list;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setList(List<T> object) {
        this.list = object;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
