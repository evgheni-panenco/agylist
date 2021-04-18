package com.agylist.service;

import com.agylist.dto.TaskDTO;
import com.agylist.dto.UpdateTaskRequest;
import com.agylist.dto.mapper.TaskMapper;
import com.agylist.dto.mapper.UpdateTaskRequestMapper;
import com.agylist.exception.ResourceAlreadyExist;
import com.agylist.exception.ResourceNotFoundException;
import com.agylist.model.Profile;
import com.agylist.model.Task;
import com.agylist.repository.ProfileRepository;
import com.agylist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	private final UpdateTaskRequestMapper updateTaskRequestMapper;
	private final ProfileRepository profileRepository;

	@Override
	public Task save(final TaskDTO taskDTO) {
		val alreadyExist = taskRepository.findByTitle(taskDTO.getTitle()).isPresent();
		if (alreadyExist) {
			throw new ResourceAlreadyExist("Task with specified title already exist");
		}
		val task = taskMapper.apply(taskDTO);
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(final String taskId) {
		return taskRepository.findById(UUID.fromString(taskId))
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find task with specified ID - " + taskId));
	}

	@Override
	public void deleteTaskById(final String taskId) {
		val id = UUID.fromString(taskId);
		taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find task for specified ID - " + taskId));
		taskRepository.deleteById(id);
	}

	@Override
	public Task updateTaskById(final UpdateTaskRequest updateTaskRequest, final String taskId) {
		val id = UUID.fromString(taskId);
		val profile = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find task for specified ID - " + taskId));
		updateTaskRequestMapper.apply(updateTaskRequest, profile);
		return taskRepository.save(profile);
	}

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task assign(final String taskId, final String profileId) {
		val task = this.getTaskById(taskId);
		val profile = profileRepository.findById(UUID.fromString(profileId))
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find profile with ID - " + profileId));
		task.setAssignee(profile);
		return taskRepository.save(task);
	}

	@Override
	public Task unassigne(final String taskId) {
		val task = this.getTaskById(taskId);
		task.setAssignee(null);
		return taskRepository.save(task);
	}

	@Override
	public void unassigneTasks(final Profile profile) {
		taskRepository.findByAssignee(profile).forEach(task -> unassigne(task.getTaskId().toString()));
	}
}
