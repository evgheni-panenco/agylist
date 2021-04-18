package com.agylist.assembler;

import com.agylist.controller.ProfileController;
import com.agylist.model.Profile;
import lombok.val;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileModelAssembler implements RepresentationModelAssembler<Profile, EntityModel<Profile>> {
	@Override
	public EntityModel<Profile> toModel(final Profile profile) {
		return EntityModel
				.of(profile,
						linkTo(methodOn(ProfileController.class)
										.getProfileById(profile.getProfileId().toString()))
										.withSelfRel(),
						linkTo(methodOn(ProfileController.class).getProfiles())
										.withRel("profiles"));
	}

	public CollectionModel<EntityModel<Profile>> toCollectionModel(final List<Profile> profiles) {
		val profileList = profiles.stream().map(this::toModel).collect(Collectors.toList());
		return CollectionModel.of(profileList, linkTo(methodOn(ProfileController.class).getProfiles()).withSelfRel());
	}
}
