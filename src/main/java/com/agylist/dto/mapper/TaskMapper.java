package com.agylist.dto.mapper;

import com.agylist.dto.TaskDTO;
import com.agylist.model.Task;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TaskMapper implements Function<TaskDTO, Task> {

	@Override
	public Task apply(TaskDTO taskDTO) {
		val task = new Task();
		BeanUtils.copyProperties(taskDTO, task);
		return task;
	}
}
