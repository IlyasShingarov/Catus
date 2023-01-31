package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.task.*;
import com.ishingarov.catus.service.ProjectService;
import com.ishingarov.catus.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/tasks")
public class ProjectTaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final TaskMapper taskMapper;

    @Operation(summary = "Получение списка задач")
    @GetMapping
    public TaskListResponse getTasks(@PathVariable Integer projectId) {
        var tasks = projectService.getProjectTasks(projectId);
        return taskMapper.toListResponse(tasks, tasks.size());
    }

    @Operation(summary = "Получение задачи")
    @GetMapping("/{taskId}")
    public TaskResponseFat getTask(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        var task = taskService.getTask(taskId);
        return taskMapper.toFatResponse(task);
    }

    @Operation(summary = "Создание задачи")
    @PostMapping
    public TaskResponseSlim createTask(@PathVariable Integer projectId, @RequestBody CreateTaskRequest request) {
        var task = taskService.createTask(taskMapper.toModel(request, projectId));
        return taskMapper.toSlimResponse(task);
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
    }

    @Operation(summary = "Изменить информацию о задаче")
    @PutMapping("/{taskId}")
    public TaskResponseSlim changeTaskInfo(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        return null;
    }

    @Operation(summary = "Изменить информацию о задаче")
    @PatchMapping("/{taskId}")
    public TaskResponseSlim changeTaskStatus(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        return null;
    }

}
