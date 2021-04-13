package com.agilyst.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {

  private String exceptionName;
  private OffsetDateTime offsetDateTime;
  private String message;
}
