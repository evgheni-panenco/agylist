package com.agilyst.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
// @Entity
public class Project {

	@Id
	@GeneratedValue
	private UUID projectId;
	private String projectName;
	private Set<Sprint> sprints;
}
