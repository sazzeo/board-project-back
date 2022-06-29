package com.jy.board.common.exception;

import com.jy.board.common.util.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final CustomResponseEntity responseEntity;

    //밸리데이터 오류 에러 (method = RequestMethod.POST, PUT, DELETE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , Object>> validError(MethodArgumentNotValidException e) {
        List<String> message = new ArrayList<>();
        for(ObjectError error :  e.getAllErrors()) {
            message.add(error.getCodes()[1]);
        }
        return responseEntity.error(message , HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String , Object>> customException(CustomException e) {
        return responseEntity.error( e.getExceptionCode() );
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String , Object>> customException(MethodArgumentTypeMismatchException e) {
        return responseEntity.error( ExceptionCode.PATH_ERROR );
    }

}
