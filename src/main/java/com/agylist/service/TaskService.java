package com.agylist.service;

import com.agylist.dto.TaskDTO;
import com.agylist.dto.UpdateTaskRequest;
import com.agylist.model.Profile;
import com.agylist.model.Task;

import java.util.List;

public interface TaskService {
	Task save(TaskDTO task);

	Task getTaskById(String taskId);

	void deleteTaskById(String taskId);

	Task updateTaskById(UpdateTaskRequest updateTaskRequest, String taskId);

	List<Task> getTasks();

	Task assign(final String taskId, final String profileId);

	Task unassigne(final String taskId);

	void unassigneTasks(final Profile profileId);
}
