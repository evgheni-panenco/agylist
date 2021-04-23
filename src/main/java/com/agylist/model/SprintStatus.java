package com.agylist.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@RequiredArgsConstructor
public enum SprintStatus {

	OPEN(1, "Open"), CLOSED(2, "Closed");

	@Id
	private final int ordinal;
	private final String description;
}
