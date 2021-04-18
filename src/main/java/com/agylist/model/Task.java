package com.agylist.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue
	private UUID taskId;
	private String title;
	private String description;

	@ManyToOne
	@JoinColumn(name = "profileId")
	private Profile assignee;

	private int storyPoints;

	@Enumerated
	private Status status;
}
