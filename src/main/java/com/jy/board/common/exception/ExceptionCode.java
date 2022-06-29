package com.jy.board.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {


    PATH_ERROR("존재하지 않는 페이지입니다."  ,HttpStatus.BAD_REQUEST) ;

    private String message;
    private String redirectPath;
    private HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.redirectPath = "/";
        this.httpStatus = httpStatus;
    }

    ExceptionCode(String message, String redirectPath, HttpStatus httpStatus) {
        this.message = message;
        this.redirectPath = redirectPath;
        this.httpStatus = httpStatus;
    }

}
