package com.agilyst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
//@Entity
@NoArgsConstructor
@AllArgsConstructor
public enum Role {

  USER(1, "User"),
  MANAGER(2, "Manager"),
  ADMIN(3, "Admin");

  @Id
  private int ordinal;
  private String name;

}
