package com.jy.board.common.exception;

import com.jy.board.common.util.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final CustomResponseEntity responseEntity;

    //밸리데이터 오류 에러 (method = RequestMethod.POST, PUT, DELETE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , Object>> validError(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return responseEntity.error( message , HttpStatus.BAD_REQUEST);
    }

}
