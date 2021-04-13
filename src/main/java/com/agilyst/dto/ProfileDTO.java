package com.agilyst.dto;

import com.agilyst.model.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfileDTO {

  private UUID profileId;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private Role role;
  private boolean isActive;
}
