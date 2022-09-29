package com.pragpooling.springboot.backend.apirest.exceptions;

import com.pragpooling.springboot.backend.apirest.entity.ErrorMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorMap> handleException(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorMap.builder().statusCode(HttpStatus.NOT_FOUND.value())
                        .cause(e.getMessage())
                        .message(e.getMessage()).build());
    }


}
