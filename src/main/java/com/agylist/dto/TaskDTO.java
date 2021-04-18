package com.agylist.dto;

import com.agylist.model.Status;
import lombok.Data;

@Data
public class TaskDTO {

  private String title;
  private String description;
  private int storyPoints;
  private Status status;
}
