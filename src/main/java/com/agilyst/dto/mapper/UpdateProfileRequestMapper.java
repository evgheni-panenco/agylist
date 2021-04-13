package com.agilyst.dto.mapper;

import com.agilyst.dto.UpdateProfileRequest;
import com.agilyst.model.Profile;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.BiFunction;

@Component
public class UpdateProfileRequestMapper implements BiFunction<UpdateProfileRequest, Profile, Profile> {

  @Override
  public Profile apply(UpdateProfileRequest updateProfileRequest, Profile profile) {
    val firstName = updateProfileRequest.getFirstName();
    if (StringUtils.hasLength(firstName)) {
      profile.setFirstName(firstName);
    }

    val lastName = updateProfileRequest.getLastName();
    if (StringUtils.hasLength(lastName)) {
      profile.setLastName(lastName);
    }

    val emailAddress = updateProfileRequest.getEmailAddress();
    if (StringUtils.hasLength(emailAddress)) {
      profile.setEmailAddress(emailAddress);
    }

    val role = updateProfileRequest.getRole();
    if (Objects.nonNull(role)) {
      profile.setRole(role);
    }

    val isActive = updateProfileRequest.getIsActive();
    if (Objects.nonNull(isActive)) {
      profile.setActive(isActive);
    }

    return profile;
  }
}
