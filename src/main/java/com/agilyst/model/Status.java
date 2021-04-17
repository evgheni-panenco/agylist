package com.agilyst.model;

import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// @Entity
@RequiredArgsConstructor
public enum Status {

	TODO(1, "To do"), IN_PROGRESS(2, "In progress"), DONE(3, "Done");

	@Id
	private final int ordinal;
	private final String description;
}
