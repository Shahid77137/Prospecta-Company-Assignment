package com.prospecta.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	  
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<ErrorDetails> noRecordHandler(MethodArgumentNotValidException ex, WebRequest req) {
		log.error("No any data in the fakeStore database");
		
        ErrorDetails ed = new ErrorDetails();
		
		ed.setTimestamp(LocalDateTime.now());
		ed.setMessage(ex.getMessage());
		ed.setUri(req.getDescription(false));
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CategoryNotFoundException.class) 
	public ResponseEntity<ErrorDetails> categoryExceptionHandler(CategoryNotFoundException ex, WebRequest req) {
		ErrorDetails ed = new ErrorDetails();
		
		ed.setTimestamp(LocalDateTime.now());
		ed.setMessage(ex.getMessage());
		ed.setUri(req.getDescription(false));
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AddProductException.class) 
	public ResponseEntity<ErrorDetails> addProductExceptionHandler(AddProductException ex, WebRequest req) {
		ErrorDetails ed = new ErrorDetails();
		
		ed.setTimestamp(LocalDateTime.now());
		ed.setMessage(ex.getMessage());
		ed.setUri(req.getDescription(false));
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}
}
