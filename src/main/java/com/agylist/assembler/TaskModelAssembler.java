package com.agylist.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.agylist.controller.TaskController;
import com.agylist.model.Task;

import lombok.val;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {

	@Override
	public EntityModel<Task> toModel(final Task task) {
		return EntityModel.of(task,
				linkTo(methodOn(TaskController.class).getTaskById(task.getTaskId().toString())).withSelfRel(),
				linkTo(methodOn(TaskController.class).getTasks()).withRel("tasks"));
	}

	public CollectionModel<EntityModel<Task>> toCollectionModel(final List<Task> profiles) {
		val taskList = profiles.stream().map(this::toModel).collect(Collectors.toList());
		return CollectionModel.of(taskList, linkTo(methodOn(TaskController.class).getTasks()).withSelfRel());
	}
}
