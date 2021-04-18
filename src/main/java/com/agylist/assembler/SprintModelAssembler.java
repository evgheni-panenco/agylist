package com.agylist.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.agylist.controller.SprintController;
import com.agylist.model.Sprint;

import lombok.val;

@Component
public class SprintModelAssembler implements RepresentationModelAssembler<Sprint, EntityModel<Sprint>> {

	@Override
	public EntityModel<Sprint> toModel(final Sprint sprint) {
		return EntityModel.of(sprint,
				linkTo(methodOn(SprintController.class).getSprintById(sprint.getSprintId().toString())).withSelfRel(),
				linkTo(methodOn(SprintController.class).getSprints()).withRel("Sprints"));
	}

	public CollectionModel<EntityModel<Sprint>> toCollectionModel(final List<Sprint> Sprints) {
		val SprintList = Sprints.stream().map(this::toModel).collect(Collectors.toList());
		return CollectionModel.of(SprintList, linkTo(methodOn(SprintController.class).getSprints()).withSelfRel());
	}
}
