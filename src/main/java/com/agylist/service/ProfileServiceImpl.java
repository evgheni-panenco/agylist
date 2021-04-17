package com.agylist.service;

import com.agylist.dto.ProfileDTO;
import com.agylist.dto.UpdateProfileRequest;
import com.agylist.dto.mapper.ProfileMapper;
import com.agylist.dto.mapper.UpdateProfileRequestMapper;
import com.agylist.exception.ResourceAlreadyExist;
import com.agylist.exception.ResourceNotFoundException;
import com.agylist.model.Profile;
import com.agylist.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;
	private final ProfileMapper profileMapper;
	private final UpdateProfileRequestMapper updateProfileRequestMapper;

	@Override
	public Profile save(final ProfileDTO profileDTO) {
		val alreadyExist = profileRepository.findByEmailAddress(profileDTO.getEmailAddress()).isPresent();
		if (alreadyExist) {
			throw new ResourceAlreadyExist("Profile with specified emailAddress already exist");
		}
		val profile = profileMapper.apply(profileDTO);
		profile.setActive(true);
		return profileRepository.save(profile);
	}

	@Override
	public Profile getProfileById(final String profileId) {
		return profileRepository.findById(UUID.fromString(profileId)).orElseThrow(
				() -> new ResourceNotFoundException("Cannot find profile with specified ID - " + profileId));
	}

	@Override
	public void deleteProfileById(final String profileId) {
		val id = UUID.fromString(profileId);
		profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cannot find profile for specified ID - " + profileId));
		profileRepository.deleteById(id);
	}

	@Override
	public Profile updateProfileById(UpdateProfileRequest updateProfileRequest, String profileId) {
		val id = UUID.fromString(profileId);
		val profile = profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Cannot find profile for specified ID - " + profileId));
		updateProfileRequestMapper.apply(updateProfileRequest, profile);
		return profileRepository.save(profile);
	}

	@Override
	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}
}
