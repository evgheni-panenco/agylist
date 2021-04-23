package com.agylist.controller;

import com.agylist.dto.Error;
import com.agylist.exception.ResourceAlreadyExist;
import com.agylist.exception.ResourceNotFoundException;
import com.agylist.exception.TaskNotClosedException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Error> handle(final ResourceAlreadyExist ex) {
		log.warn(ex.getMessage());
		return build(422, ex);
	}

	@ExceptionHandler
	public ResponseEntity<Error> handle(final ResourceNotFoundException ex) {
		log.warn(ex.getMessage());
		return build(404, ex);
	}

	@ExceptionHandler
	public ResponseEntity<Error> handle(final TaskNotClosedException ex) {
		log.warn(ex.getMessage());
		return build(409, ex);
	}

	@ExceptionHandler
	public ResponseEntity<Error> handle(final MethodArgumentNotValidException ex) {
		val message = "Validation failed for the next field - " + ex.getFieldError().getField();
		log.warn(message);
		val exceptionWithCorrectMessage = new Exception(message);
		return build(404, exceptionWithCorrectMessage);
	}

	@ExceptionHandler
	public ResponseEntity<Error> handle(final Exception ex) {
		log.warn("Unexpected error occurred - {}", ex.getMessage());
		return build(404, ex);
	}

	public ResponseEntity<Error> build(final int status, final Exception ex) {
		return ResponseEntity.status(status).body(Error.builder().exceptionName(ex.getClass().getSimpleName())
				.message(ex.getMessage()).offsetDateTime(OffsetDateTime.now().withNano(0)).build());
	}
}
