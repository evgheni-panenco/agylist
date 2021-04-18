package com.agylist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Project {

	@Id
	@GeneratedValue
	private UUID projectId;
	private String projectName;

	@ManyToOne
	@JoinColumn
	private List<Sprint> sprints;
}
