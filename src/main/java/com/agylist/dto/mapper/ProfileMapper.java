package com.agylist.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.agylist.dto.ProfileDTO;
import com.agylist.model.Profile;

import lombok.val;

@Component
public class ProfileMapper implements Function<ProfileDTO, Profile> {

	@Override
	public Profile apply(ProfileDTO profileDTO) {
		val profile = new Profile();
		BeanUtils.copyProperties(profileDTO, profile);
		return profile;
	}
}
