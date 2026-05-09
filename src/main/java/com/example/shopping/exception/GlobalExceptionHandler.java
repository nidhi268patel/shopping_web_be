package com.example.shopping.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ org.springframework.security.core.AuthenticationException.class,
			io.jsonwebtoken.JwtException.class })
	public ResponseEntity<?> handleAuthException(Exception ex) {
		return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired token", "status", 401));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException ex) {
		return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage(), "status", 400));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		return ResponseEntity.status(500).body(Map.of("message", "Something went wrong", "status", 500));
	}
}