package com.agylist.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agylist.assembler.ProfileModelAssembler;
import com.agylist.dto.ProfileDTO;
import com.agylist.dto.UpdateProfileRequest;
import com.agylist.model.Profile;
import com.agylist.service.ProfileService;

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
	private final ProfileModelAssembler profileModelAssembler;

	@PostMapping
	public ResponseEntity<EntityModel<Profile>> saveProfile(@RequestBody final ProfileDTO profile) {
		log.info("Saving profile to database...");
		val createdProfile = profileService.save(profile);
		log.info("Profile with id - {} was saved in database", createdProfile.getProfileId());
		return ResponseEntity.ok(profileModelAssembler.toModel(createdProfile));
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Profile>>> getProfiles() {
		log.info("Getting list of profiles from database...");
		val profiles = profileService.getProfiles();
		log.info("Response with retrieved list of profiles");
		return ResponseEntity.ok(profileModelAssembler.toCollectionModel(profiles));
	}

	@GetMapping(ONE_URL)
	public ResponseEntity<EntityModel<Profile>> getProfileById(@PathVariable final String profileId) {
		log.info("Getting profile by ID from database...");
		val profile = profileService.getProfileById(profileId);
		log.info("Response with retrieved profile");
		return ResponseEntity.ok(profileModelAssembler.toModel(profile));
	}

	@DeleteMapping(ONE_URL)
	public ResponseEntity<Void> deleteProfile(@PathVariable final String profileId) {
		log.info("Deleting profile from database...");
		profileService.deleteProfileById(profileId);
		log.info("Profile with id - {} was deleted from database", profileId);
		return ResponseEntity.status(204).build();
	}

	@PatchMapping(ONE_URL)
	public ResponseEntity<EntityModel<Profile>> updateProfile(@PathVariable final String profileId,
			@RequestBody final UpdateProfileRequest updateProfileRequest) {
		log.info("Updating profile...");
		val profile = profileService.updateProfileById(updateProfileRequest, profileId);
		log.info("Profile with id - {} was updated", profileId);
		return ResponseEntity.ok(profileModelAssembler.toModel(profile));
	}
}
