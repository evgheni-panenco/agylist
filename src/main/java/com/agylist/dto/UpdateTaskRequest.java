package com.agylist.dto;

import com.agylist.model.Status;
import lombok.Data;

@Data
public class UpdateTaskRequest {

  private String title;
  private String description;
  private Integer storyPoints;
  private Status status;
}
