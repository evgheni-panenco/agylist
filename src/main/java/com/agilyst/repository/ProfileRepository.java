package com.agilyst.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agilyst.model.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, UUID> {

	Optional<Profile> findByEmailAddress(final String emailAddress);

}
