package com.agylist.service;

import com.agylist.dto.ProfileDTO;
import com.agylist.dto.UpdateProfileRequest;
import com.agylist.model.Profile;

import java.util.List;

public interface ProfileService {
	Profile save(ProfileDTO profile);

	Profile getProfileById(String profileId);

	void deleteProfileById(String profileId);

	Profile updateProfileById(UpdateProfileRequest updateProfileRequest, String profileId);

	List<Profile> getProfiles();
}
