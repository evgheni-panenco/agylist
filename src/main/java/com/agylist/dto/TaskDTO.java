package com.agylist.dto;

import com.agylist.model.Status;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TaskDTO {

  @NotBlank(message = "Field Title is required")
  @Size(min = 5, max = 90, message = "The length of Title should be between 5 and 90")
  private String title;
  private String description;

  @NotNull
  @Min(1)
  @Max(13)
  private int storyPoints;
  private Status status;
}
