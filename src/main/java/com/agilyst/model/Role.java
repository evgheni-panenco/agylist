package com.agilyst.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public enum Role {

  USER(1, "User"),
  MANAGER(2, "Manager"),
  ADMIN(3, "Admin");

  @Id
  private int ordinal;

  @JsonValue
  private String name;

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public Role fromString(final String name) {
    return Arrays.stream(Role.values())
            .filter(v -> name.compareToIgnoreCase(v.getName()) == 0)
            .findAny().orElseThrow(() ->
              new EnumConstantNotPresentException(Role.class, name));
  }
}
