package com.agylist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agylist.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, UUID> {

	Optional<Sprint> findBySprintName(String sprintName);
}
