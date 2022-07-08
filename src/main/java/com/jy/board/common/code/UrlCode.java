package com.jy.board.common.code;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UrlCode {

    BaseUrl("/api/board"),
    PostUrl("/api/board/post"),
    MemberUrl("/api/board/member"),
    AuthUrl("/api/board/auth");

    public String url;

    UrlCode(String url) {
        this.url = url;
    }

}
