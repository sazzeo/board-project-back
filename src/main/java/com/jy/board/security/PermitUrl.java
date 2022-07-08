package com.jy.board.security;

import com.jy.board.common.code.UrlCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum PermitUrl {

    GET(addPrefix(UrlCode.BaseUrl , new String[]{"/login", "/join"})),
    POST(addPrefix(UrlCode.AuthUrl , new String[]{"/login", "/join"})),
    PUT(),
    DELETE(),
    ANY();

    private String[] urls;

    PermitUrl(String[] urls) {
        this.urls = urls;
    }

    private static String[] addPrefix(UrlCode urlCode, String[] urls) {

        String[] newUrls = new String[urls.length];
        String prefix = urlCode.getUrl();
        for(int i = 0 ; i < urls.length ; i++) {
            newUrls[i] = prefix + urls[i];
        }

        return newUrls;
    }

}
