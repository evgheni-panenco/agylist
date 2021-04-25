package com.agylist.service;

import com.agylist.dto.SprintDTO;
import com.agylist.dto.UpdateSprintRequest;
import com.agylist.exception.ResourceAlreadyExist;
import com.agylist.exception.ResourceNotFoundException;
import com.agylist.exception.TaskNotClosedException;
import com.agylist.model.Sprint;
import com.agylist.model.SprintStatus;
import com.agylist.repository.SprintRepository;
import com.agylist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.agylist.model.Status.DONE;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

	private final SprintRepository sprintRepository;
	private final TaskRepository taskRepository;

	@Override
	public Sprint save(SprintDTO sprintDTO) {
		val alreadyExist = sprintRepository.findBySprintName(sprintDTO.getSprintName()).isPresent();
		if (alreadyExist) {
			throw new ResourceAlreadyExist("Sprint with specified sprintName already exist");
		}
		val sprint = setUpSprint(sprintDTO.getSprintName());
		return sprintRepository.save(sprint);
	}

	private Sprint setUpSprint(final String sprintName) {
		return Sprint.builder()
						.sprintName(sprintName)
						.createdTimestamp(OffsetDateTime.now().withNano(0))
						.status(SprintStatus.OPEN)
						.build();
	}

	@Override
	public Sprint getSprintById(String sprintId) {
		return sprintRepository.findById(UUID.fromString(sprintId))
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find sprint with specified ID - " + sprintId));
	}

	@Override
	public void deleteSprintById(String sprintId) {
		val sprint = this.getSprintById(sprintId);
		sprintRepository.delete(sprint);
	}

	@Override
	public Sprint updateSprintById(UpdateSprintRequest updateSprintRequest, String sprintId) {
		val sprint = this.getSprintById(sprintId);
		sprint.setSprintName(updateSprintRequest.getSprintName());
		return sprintRepository.save(sprint);
	}

	@Override
	public List<Sprint> getSprints() {
		return sprintRepository.findAll();
	}

	@Override
	public Sprint addTaskToSprint(String sprintId, String taskId) {
		val task = taskRepository.findById(UUID.fromString(taskId))
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find task with specified ID - " + taskId));
		val sprint = this.getSprintById(sprintId);
		sprint.getTasks().add(task);
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint removeTaskFromSprint(String sprintId, String taskId) {
		val task = taskRepository.findById(UUID.fromString(taskId))
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find task with specified ID - " + taskId));
		val sprint = this.getSprintById(sprintId);
		sprint.getTasks().remove(task);
		return sprintRepository.save(sprint);
	}

	@Override
	public void close(String sprintId) {
		val sprint = this.getSprintById(sprintId);
		val hasUndoneTasks = sprint.getTasks().stream().anyMatch(task -> task.getStatus() != DONE);
		if (hasUndoneTasks) {
			throw new TaskNotClosedException("Cannot close sprint with unclosed task");
		}
		sprint.setStatus(SprintStatus.CLOSED);
		sprintRepository.save(sprint);
	}
}
