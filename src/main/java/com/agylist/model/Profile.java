package com.agylist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

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
