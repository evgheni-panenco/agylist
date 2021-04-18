package com.agylist.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SprintDTO {

	@NotBlank
	private String sprintName;
}
