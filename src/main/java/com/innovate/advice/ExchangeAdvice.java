package com.innovate.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExchangeAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultErrorHandler(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
