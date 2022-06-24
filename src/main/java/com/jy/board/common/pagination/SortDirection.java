package com.jy.board.common.pagination;

import lombok.Getter;

@Getter
public enum SortDirection {


    ASC("ASC"),
    DESC("DESC");

    private String direction;

    SortDirection(String direction) {
        this.direction = direction;
    }


}
