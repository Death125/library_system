package com.library.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.exceptions.BookNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> methodArgumentExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookNotFoundException.class)
    private Map<String, String> bookNotFoundException(BookNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message : ", ex.getMessage());
        return errorMap;
    }
}
