package com.agilyst.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
// @Entity
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
