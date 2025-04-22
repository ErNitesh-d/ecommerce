package com.example.demo.config;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex,
			HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", ex.getStatusCode().value());
		body.put("error", ex.getReason()); // ✅ use your custom message here
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, ex.getStatusCode());
	}

	// Optional: Catch all unhandled exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", 500);
		body.put("error", "Unexpected error: " + ex.getMessage());
		body.put("path", request.getRequestURI());

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
