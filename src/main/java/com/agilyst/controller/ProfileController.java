package com.agilyst.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agilyst.dto.ProfileDTO;
import com.agilyst.dto.UpdateProfileRequest;
import com.agilyst.model.Profile;
import com.agilyst.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProfileController.BASE_URL)
public class ProfileController {

	public static final String BASE_URL = "/profile";
	public static final String ONE_URL = "/{profileId}";

	private final ProfileService profileService;

	@PostMapping
	public ResponseEntity<Profile> saveProfile(@RequestBody final ProfileDTO profile) {
		log.info("Saving profile to database...");
		val createdProfile = profileService.save(profile);
		log.info("Profile with id - {} was saved in database", createdProfile.getProfileId());
		return ResponseEntity.ok(createdProfile);
	}

	@GetMapping(ONE_URL)
	public ResponseEntity<Profile> getProfileById(@PathVariable final String profileId) {
		log.info("Getting profile by ID from database...");
		val profile = profileService.getProfileById(profileId);
		log.info("Response with retrieved profile");
		return ResponseEntity.ok(profile);
	}

	@GetMapping
	public ResponseEntity<Profile> getProfileByEmail(@RequestParam final String emailAddress) {
		log.info("Getting profile by email from database...");
		val profile = profileService.getProfileByEmail(emailAddress);
		log.info("Response with retrieved profile");
		return ResponseEntity.ok(profile);
	}

	@DeleteMapping(ONE_URL)
	public ResponseEntity<Void> deleteProfile(@PathVariable final String profileId) {
		log.info("Deleting profile from database...");
		profileService.deleteProfileById(profileId);
		log.info("Profile with id - {} was deleted from database", profileId);
		return ResponseEntity.status(204).build();
	}

	@PatchMapping(ONE_URL)
	public ResponseEntity<Profile> updateProfile(@PathVariable final String profileId,
			@RequestBody final UpdateProfileRequest updateProfileRequest) {
		log.info("Updating profile...");
		val profile = profileService.updateProfileById(updateProfileRequest, profileId);
		log.info("Profile with id - {} was updated", profileId);
		return ResponseEntity.ok(profile);
	}
}
