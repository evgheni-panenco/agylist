package com.agylist.service;

import com.agylist.dto.SprintDTO;
import com.agylist.dto.UpdateSprintRequest;
import com.agylist.model.Sprint;

import java.util.List;

public interface SprintService {

	Sprint save(SprintDTO sprintDTO);

	Sprint getSprintById(String sprintId);

	void deleteSprintById(String sprintId);

	Sprint updateSprintById(UpdateSprintRequest updateSprintRequest, String sprintId);

	List<Sprint> getSprints();

	Sprint addTaskToSprint(String sprintId, String taskId);

	Sprint removeTaskFromSprint(String sprintId, String taskId);

  void close(String sprintId);
}
