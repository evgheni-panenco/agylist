package com.agylist.model;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
// @Entity
public class Sprint {

	@Id
	@GeneratedValue
	private UUID sprintId;
	private String sprintName;

	private Set<Task> tasks;

	private OffsetDateTime createdOn;
	private OffsetDateTime endsOn;
}
