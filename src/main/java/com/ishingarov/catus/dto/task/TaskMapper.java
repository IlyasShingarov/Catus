package com.ishingarov.catus.dto.task;

import com.ishingarov.catus.dto.user.UserMapper;
import com.ishingarov.catus.model.domain.TaskModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TaskMapper {

    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "taskStatus", source = "request.status")
    @Mapping(target = "projectId", source = "projectId")
    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", ignore = true)
    TaskModel toModel(CreateTaskRequest request, Integer projectId);

    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "taskStatus", source = "request.status")
    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "taskId")
    TaskModel toModel(UpdateTaskRequest request, Integer projectId, Integer taskId);

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "dueDate", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "createDttm", ignore = true)
    @Mapping(target = "taskStatus", source = "request.status")
    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "taskId")
    TaskModel toModel(UpdateTaskStatusRequest request, Integer projectId, Integer taskId);

    @Mapping(target = "status", source = "model.taskStatus")
    @Mapping(target = "commentCount", source = "model.commentCount")
    TaskResponseSlim toSlimResponse(TaskModel model);

    @Mapping(target = "status", source = "taskStatus")
    @Mapping(target = "creator", source = "author")
    TaskResponseFat toFatResponse(TaskModel model);

    @Mapping(target = "tasks", source = "models")
    TaskListResponse toListResponse(List<TaskModel> models, Integer total);
}
