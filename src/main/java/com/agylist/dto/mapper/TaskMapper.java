package com.agylist.dto.mapper;

import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.agylist.dto.TaskDTO;
import com.agylist.model.Task;

import lombok.val;

@Component
public class TaskMapper implements Function<TaskDTO, Task> {

	@Override
	public Task apply(TaskDTO taskDTO) {
		val task = new Task();
		BeanUtils.copyProperties(taskDTO, task);
		return task;
	}
}
