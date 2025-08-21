package com.delta.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleAll(Exception ex){
		Map<String,String> error= new HashMap<>();
		error.put("error", ex.getMessage());
		return ResponseEntity.badRequest().body(error);
		
	}
}
