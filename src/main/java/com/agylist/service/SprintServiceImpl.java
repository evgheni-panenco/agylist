package com.agylist.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agylist.dto.SprintDTO;
import com.agylist.dto.UpdateSprintRequest;
import com.agylist.exception.ResourceAlreadyExist;
import com.agylist.exception.ResourceNotFoundException;
import com.agylist.model.Sprint;
import com.agylist.repository.SprintRepository;
import com.agylist.repository.TaskRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

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
		return Sprint.builder().sprintName(sprintName).createdTimestamp(OffsetDateTime.now().withNano(0)).build();
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
}
