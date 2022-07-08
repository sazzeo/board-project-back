package com.jy.board.common.util;

import com.jy.board.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomResponseEntity<T> {

    public ResponseEntity<Map<String, Object>> error(String message , HttpStatus status) {
        Map<String , Object> map = new HashMap<>();
        map.put("message" , message );
        map.put("status" , status.value());
        return new ResponseEntity<>(map , status);

    }

    public ResponseEntity<Map<String, Object>> error(List<Object> message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        return new ResponseEntity<>(map, status);

    }

    public ResponseEntity<Map<String, Object>> error(ExceptionCode exceptionCode) {
        Map<String , Object> map = new HashMap<>();
        map.put("message" , exceptionCode.getMessage() );
        map.put("status" , exceptionCode.getHttpStatus());
        map.put("redirectPath" , exceptionCode.getRedirectPath());
        return new ResponseEntity<>(map , exceptionCode.getHttpStatus());

    }




    public ResponseEntity<T> success(T object , HttpStatus status) {
        return new ResponseEntity<T>(object , status);
    }

}
