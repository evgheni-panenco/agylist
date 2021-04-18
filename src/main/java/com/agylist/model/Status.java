package com.agylist.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@RequiredArgsConstructor
public enum Status {

	TODO(1, "To do"), IN_PROGRESS(2, "In progress"), DONE(3, "Done");

	@Id
	private final int ordinal;
	private final String description;
}
