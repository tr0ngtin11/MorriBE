package com.sample.demo.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> noSuchElementExceptionHandler(Exception ex, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
        log.error(String.valueOf(errorMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> IllegalArgumentExceptionHandler(Exception ex,WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
        log.error(String.valueOf(errorMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> DataAccessExceptionHandler(Exception ex,WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
        log.error(String.valueOf(errorMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
