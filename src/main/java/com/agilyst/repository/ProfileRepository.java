package com.agilyst.repository;

import com.agilyst.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, UUID> {

  Optional<Profile> findByEmailAddress(final String emailAddress);

}
