package com.agilyst.service;

import com.agilyst.dto.ProfileDTO;
import com.agilyst.dto.UpdateProfileRequest;
import com.agilyst.model.Profile;

public interface ProfileService {
  Profile save(ProfileDTO profile);

  Profile getProfileByEmail(String emailAddress);

  Profile getProfileById(String profileId);

  void deleteProfileById(String profileId);

  Profile updateProfileById(UpdateProfileRequest updateProfileRequest, String profileId);
}
