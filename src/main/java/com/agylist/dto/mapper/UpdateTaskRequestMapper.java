package com.agylist.dto.mapper;

import java.util.Objects;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.agylist.dto.UpdateTaskRequest;
import com.agylist.model.Task;

@Component
public class UpdateTaskRequestMapper implements BiFunction<UpdateTaskRequest, Task, Task> {

	@Override
	public Task apply(UpdateTaskRequest updateTaskRequest, Task task) {
		if (StringUtils.hasLength(updateTaskRequest.getTitle())) {
			task.setTitle(updateTaskRequest.getTitle());
		}

		if (StringUtils.hasLength(updateTaskRequest.getDescription())) {
			task.setDescription(updateTaskRequest.getDescription());
		}

		if (Objects.nonNull(updateTaskRequest.getStoryPoints())) {
			task.setStoryPoints(updateTaskRequest.getStoryPoints());
		}

		if (Objects.nonNull(updateTaskRequest.getStatus())) {
			task.setStatus(updateTaskRequest.getStatus());
		}

		return task;
	}
}
