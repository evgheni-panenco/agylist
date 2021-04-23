package com.agylist.controller;

import com.agylist.assembler.SprintModelAssembler;
import com.agylist.dto.SprintDTO;
import com.agylist.dto.UpdateSprintRequest;
import com.agylist.model.Sprint;
import com.agylist.service.SprintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static com.agylist.controller.SprintController.BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class SprintController {

	public static final String BASE_URL = "/sprint";
	public static final String ONE_URL = "/{sprintId}";
	public static final String ADD_TASK = ONE_URL + "/add/{taskId}";
	public static final String REMOVE_TASK = ONE_URL + "/remove/{taskId}";

	public static final String UUID_PATTERN = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";

	private final SprintService sprintService;
	private final SprintModelAssembler sprintModelAssembler;

	@PostMapping
	public ResponseEntity<EntityModel<Sprint>> saveSprint(@RequestBody @Valid final SprintDTO sprint) {
		log.info("Saving Sprint to database...");
		val createdSprint = sprintService.save(sprint);
		log.info("Sprint with id - {} was saved in database", createdSprint.getSprintId());
		return ResponseEntity.ok(sprintModelAssembler.toModel(createdSprint));
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Sprint>>> getSprints() {
		log.info("Getting list of Sprints from database...");
		val sprints = sprintService.getSprints();
		log.info("Response with retrieved list of Sprints");
		return ResponseEntity.ok(sprintModelAssembler.toCollectionModel(sprints));
	}

	@GetMapping(ONE_URL)
	public ResponseEntity<EntityModel<Sprint>> getSprintById(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId) {
		log.info("Getting Sprint by ID from database...");
		val sprint = sprintService.getSprintById(sprintId);
		log.info("Response with retrieved Sprint");
		return ResponseEntity.ok(sprintModelAssembler.toModel(sprint));
	}

	@DeleteMapping(ONE_URL)
	public ResponseEntity<Void> deleteSprintById(@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId) {
		log.info("Deleting Sprint from database...");
		sprintService.deleteSprintById(sprintId);
		log.info("Sprint with id - {} was deleted from database", sprintId);
		return ResponseEntity.status(204).build();
	}

	@PatchMapping(ONE_URL)
	public ResponseEntity<EntityModel<Sprint>> updateSprint(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId,
			@RequestBody final UpdateSprintRequest updateSprintRequest) {
		log.info("Updating Sprint...");
		val sprint = sprintService.updateSprintById(updateSprintRequest, sprintId);
		log.info("Sprint with id - {} was updated", sprintId);
		return ResponseEntity.ok(sprintModelAssembler.toModel(sprint));
	}

	@PutMapping(ADD_TASK)
	public ResponseEntity<EntityModel<Sprint>> addTaskToSprint(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId,
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId) {
		log.info("Adding task to Sprint...");
		val sprint = sprintService.addTaskToSprint(sprintId, taskId);
		log.info("Task has been added to Sprint");
		return ResponseEntity.ok(sprintModelAssembler.toModel(sprint));
	}

	@DeleteMapping(REMOVE_TASK)
	public ResponseEntity<EntityModel<Sprint>> removeTaskFromSprint(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId,
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId) {
		log.info("Removing task to Sprint...");
		val sprint = sprintService.removeTaskFromSprint(sprintId, taskId);
		log.info("Task has been removed from Sprint");
		return ResponseEntity.ok(sprintModelAssembler.toModel(sprint));
	}

	@PostMapping(ONE_URL)
	public ResponseEntity<Void> closeSprint(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String sprintId) {
		log.info("Closing the Spring with ID - " + sprintId);
		sprintService.close(sprintId);
		log.info("Spring with ID - {}, has been closed", sprintId);
		return ResponseEntity.noContent().build();
	}
}
