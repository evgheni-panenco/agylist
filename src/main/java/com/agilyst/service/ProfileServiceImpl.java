package com.agilyst.service;

import com.agilyst.dto.ProfileDTO;
import com.agilyst.dto.UpdateProfileRequest;
import com.agilyst.dto.mapper.ProfileMapper;
import com.agilyst.dto.mapper.UpdateProfileRequestMapper;
import com.agilyst.exception.ResourceAlreadyExist;
import com.agilyst.exception.ResourceNotFoundException;
import com.agilyst.model.Profile;
import com.agilyst.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

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
	public Profile getProfileByEmail(final String emailAddress) {
		return profileRepository.findByEmailAddress(emailAddress).orElseThrow(
				() -> new ResourceNotFoundException("Cannot find profile with specified email - " + emailAddress));
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
}
