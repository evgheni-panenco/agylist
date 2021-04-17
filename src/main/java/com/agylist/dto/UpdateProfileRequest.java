package com.agylist.dto;

import com.agylist.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private Role role;
	private Boolean isActive;
}
