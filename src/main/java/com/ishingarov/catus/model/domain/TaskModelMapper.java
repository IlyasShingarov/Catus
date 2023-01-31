package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserModelMapper.class)
public interface TaskModelMapper {

    @Mapping(target = "author", source = "entity.createdBy")
    @Mapping(target = "taskStatus", source = "entity.status")
    @Mapping(target = "projectId", source = "entity.project.id")
    @Mapping(target = "commentCount", expression = "java(entity.getComments() != null ? entity.getComments().size() : 0)")
    TaskModel toModel(Task entity);

    List<TaskModel> toModel(List<Task> entity);

    @Mapping(target = "status", source = "model.taskStatus")
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createDttm", ignore = true)
    Task toEntity(TaskModel model);
}
