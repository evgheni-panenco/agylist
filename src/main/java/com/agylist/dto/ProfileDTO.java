package com.agylist.dto;

import com.agylist.model.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileDTO {

	private String firstName;
	private String lastName;

	@NotBlank
	private String emailAddress;

	private Role role;
	private boolean isActive;
}
