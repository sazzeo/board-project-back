package com.jy.board.common.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionCode {


    PATH_ERROR("존재하지 않는 페이지입니다." , HttpStatus.BAD_REQUEST) ;

    private String message;
    private HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
