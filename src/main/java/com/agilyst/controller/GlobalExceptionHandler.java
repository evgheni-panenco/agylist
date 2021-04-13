package com.agilyst.controller;

import com.agilyst.dto.Error;
import com.agilyst.exception.ResourceAlreadyExist;
import com.agilyst.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<Error> handle(final ResourceAlreadyExist ex) {
    return build(422, ex);
  }

  @ExceptionHandler
  public ResponseEntity<Error> handle(final ResourceNotFoundException ex) {
    return build(404, ex);
  }

  public ResponseEntity<Error>
  build(final int status, final Exception ex) {
    return ResponseEntity.status(status).body(Error.builder()
            .exceptionName(ex.getClass().getSimpleName())
            .message(ex.getMessage())
            .offsetDateTime(OffsetDateTime.now().withNano(0))
            .build());
  }
}
