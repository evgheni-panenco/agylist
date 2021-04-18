package com.agylist.repository;

import com.agylist.model.Profile;
import com.agylist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

  Optional<Task> findByTitle(final String title);

  Stream<Task> findByAssignee(final Profile profile);

}
