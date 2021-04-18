package com.agylist.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Profile {

	@Id
	@GeneratedValue
	private UUID profileId;

	private String firstName;
	private String lastName;

	private String emailAddress;

	@Enumerated
	private Role role;

	private boolean isActive;
}
