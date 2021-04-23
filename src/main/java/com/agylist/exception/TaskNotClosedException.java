package com.agylist.exception;

public class TaskNotClosedException extends RuntimeException {
  public TaskNotClosedException(final String message) {
    super(message);
  }
}
