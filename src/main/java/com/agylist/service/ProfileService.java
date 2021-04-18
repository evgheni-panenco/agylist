package com.agylist.service;

import java.util.List;

import com.agylist.dto.ProfileDTO;
import com.agylist.dto.UpdateProfileRequest;
import com.agylist.model.Profile;

public interface ProfileService {
	Profile save(ProfileDTO profile);

	Profile getProfileById(String profileId);

	void deleteProfileById(String profileId);

	Profile updateProfileById(UpdateProfileRequest updateProfileRequest, String profileId);

	List<Profile> getProfiles();
}
