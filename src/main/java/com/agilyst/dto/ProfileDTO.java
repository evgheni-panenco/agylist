package com.agilyst.dto;

import java.util.UUID;

import com.agilyst.model.Role;

import lombok.Data;

@Data
public class ProfileDTO {

	private UUID profileId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Role role;
	private boolean isActive;
}
