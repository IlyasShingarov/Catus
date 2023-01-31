package com.ishingarov.catus.dto.project;

import com.ishingarov.catus.dto.task.TaskMapper;
import com.ishingarov.catus.model.domain.ProjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ProjectMapper {

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    ProjectModel toModel(CreateProjectRequest request);

    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "id", source = "projectId")
    ProjectModel toModel(UpdateProjectRequest request, Integer projectId);

    @Mapping(target = "createdAt", source = "createDttm")
    @Mapping(target = "taskCount", expression = "java(model.tasks() != null ? model.tasks().size() : 0)")
    @Mapping(target = "userCount", expression = "java(model.users() != null ? model.users().size() : 0)")
    ProjectResponseSlim toSlimResponse(ProjectModel model);

    @Mapping(target = "userCount", expression = "java(model.users() != null ? model.users().size() : 0)")
    @Mapping(target = "createdAt", source = "createDttm")
    ProjectResponse toResponse(ProjectModel model);

    @Mapping(target = "projects", source = "models")
    ProjectListResponse toListResponse(List<ProjectModel> models, Integer total);

}
