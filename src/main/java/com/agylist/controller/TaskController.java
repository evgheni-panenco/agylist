package com.agylist.controller;

import com.agylist.assembler.TaskModelAssembler;
import com.agylist.dto.TaskDTO;
import com.agylist.dto.UpdateTaskRequest;
import com.agylist.model.Task;
import com.agylist.service.TaskService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

	public static final String BASE_URL = "/task";
	public static final String ONE_URL = "/{taskId}";
	public static final String ASSIGN_TASK = ONE_URL + "/profile/{profileId}";
	public static final String UNASSIGN_TASK = "/unassign" + ONE_URL;
	public static final String UUID_PATTERN = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";

	private final TaskService taskService;
	private final TaskModelAssembler taskModelAssembler;

	@PostMapping
	public ResponseEntity<EntityModel<Task>> createTask(@RequestBody @Valid final TaskDTO taskDTO) {
		log.info("Creating a task...");
		val savedTask = taskService.save(taskDTO);
		log.info("Task has been created - {}", savedTask.getTaskId());
		return ResponseEntity.ok(taskModelAssembler.toModel(savedTask));
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Task>>> getTasks() {
		log.info("Getting list of tasks");
		val tasks = taskService.getTasks();
		log.info("Returning list of tasks");
		return ResponseEntity.ok(taskModelAssembler.toCollectionModel(tasks));
	}

	@GetMapping(ONE_URL)
	public ResponseEntity<EntityModel<Task>> getTaskById(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId) {
		log.info("Getting task by id");
		val task = taskService.getTaskById(taskId);
		log.info("Returning task with ID - {}", task.getTaskId());
		return ResponseEntity.ok(taskModelAssembler.toModel(task));
	}

	@DeleteMapping(ONE_URL)
	public ResponseEntity<Void> deleteTaskById(@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId) {
		log.info("Deleting task by id");
		taskService.deleteTaskById(taskId);
		log.info("Task with ID - {}, has been deleted", taskId);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(ONE_URL)
	public ResponseEntity<EntityModel<Task>> updateTaskById(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId,
			@RequestBody final UpdateTaskRequest updateTaskRequest) {
		log.info("Updating task...");
		val updatedTask = taskService.updateTaskById(updateTaskRequest, taskId);
		log.info("Task with ID - {}, has been updated", updatedTask.getTaskId());
		return ResponseEntity.ok(taskModelAssembler.toModel(updatedTask));
	}

	@PutMapping(ASSIGN_TASK)
	public ResponseEntity<EntityModel<Task>> assignTask(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId, @PathVariable final String profileId) {
		log.info("Assigning task...");
		val task = taskService.assign(taskId, profileId);
		log.info("Task with ID - {}, has been assign to profileId - {}", taskId, profileId);
		return ResponseEntity.ok(taskModelAssembler.toModel(task));
	}

	@PutMapping(UNASSIGN_TASK)
	public ResponseEntity<EntityModel<Task>> assignTask(
			@PathVariable @Pattern(regexp = UUID_PATTERN) final String taskId) {
		log.info("Unassigning task...");
		val task = taskService.unassigne(taskId);
		log.info("Task has been unassigned {}", taskId);
		return ResponseEntity.ok(taskModelAssembler.toModel(task));
	}

}
