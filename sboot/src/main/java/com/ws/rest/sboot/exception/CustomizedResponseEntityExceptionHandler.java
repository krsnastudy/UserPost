package com.ws.rest.sboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ws.rest.sboot.controller.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
		extends ResponseEntityExceptionHandler{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
			(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse oExceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		ResponseEntity oResponseEntity = new ResponseEntity(oExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return oResponseEntity;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
			(UserNotFoundException ex, WebRequest request) throws Exception {
		
		ExceptionResponse oExceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		ResponseEntity oResponseEntity = new ResponseEntity(oExceptionResponse, HttpStatus.NOT_FOUND);
		
		return oResponseEntity;
	}
}