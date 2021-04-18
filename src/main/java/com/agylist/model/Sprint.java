package com.agylist.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

	@Id
	@GeneratedValue
	private UUID sprintId;

	private String sprintName;

	@OneToMany
	@JoinColumn
	private List<Task> tasks;

	private OffsetDateTime createdTimestamp;
}
