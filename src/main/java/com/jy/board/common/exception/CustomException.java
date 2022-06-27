package com.jy.board.common.exception;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
    }


    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }


}
