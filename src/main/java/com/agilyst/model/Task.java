package com.agilyst.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
//@Entity
public class Task {

  @Id
  @GeneratedValue
  private UUID taskId;
  private String title;
  private String description;
  private Profile assignee;
  private int storyPoints;
  private Status status;
}
