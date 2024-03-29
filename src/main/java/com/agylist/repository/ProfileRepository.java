package com.agylist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agylist.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

	Optional<Profile> findByEmailAddress(final String emailAddress);

}
