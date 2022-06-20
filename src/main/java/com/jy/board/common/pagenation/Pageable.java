package com.jy.board.common.pagenation;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pageable {

    private int size;
    private int page;

}
