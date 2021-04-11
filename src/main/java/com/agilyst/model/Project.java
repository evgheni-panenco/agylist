package com.agilyst.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;
import java.util.UUID;

@Data
//@Entity
public class Project {

  @Id
  @GeneratedValue
  private UUID projectId;
  private String projectName;
  private Set<Sprint> sprints;
}
