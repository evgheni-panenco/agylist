package com.agilyst.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
//@Entity
public class Sprint {

  @Id
  @GeneratedValue
  private UUID sprintId;
  private String sprintName;
  private Set<Task> tasks;

  private OffsetDateTime createdOn;
  private OffsetDateTime endsOn;
}
