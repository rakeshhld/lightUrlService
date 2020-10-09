package com.rakesh.lightUrlService.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.model.ResponseError;

@ControllerAdvice()
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(URLEmptyException.class)
	public final ResponseEntity<ResponseError> urlEmpty(URLEmptyException e){
		ResponseError re = new ResponseError(e.getMessage(),e.getHint());
		return new ResponseEntity<ResponseError>(re,HttpStatus.CONFLICT);
	}
}
