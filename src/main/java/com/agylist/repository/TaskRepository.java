package com.agylist.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agylist.model.Profile;
import com.agylist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

	Optional<Task> findByTitle(final String title);

	Stream<Task> findByAssignee(final Profile profile);

}
