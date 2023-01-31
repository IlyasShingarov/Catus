package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.project.*;
import com.ishingarov.catus.dto.user.UserListResponse;
import com.ishingarov.catus.dto.user.UserMapper;
import com.ishingarov.catus.model.domain.UserModel;
import com.ishingarov.catus.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Tag(name = "Проекты", description = "Методы взаимодействия с коллекцией проектов")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final static String baseUrl = "/api/v1/projects/";

    @Operation(summary = "Получение проекта по его идентификатору")
    @GetMapping("/{projectId}")
    public ProjectResponseSlim getProject(@PathVariable Integer projectId) {
        log.trace("Got GET request on {}/{}", baseUrl, projectId);

        log.debug("Fetching project with id '{}'", projectId);
        var project = projectService.getProject(projectId);

        var response = projectMapper.toSlimResponse(project);
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Получение всех пользователей участвующих в проекте")
    @GetMapping("/{projectId}/users")
    public UserListResponse getProjectUsers(@PathVariable Integer projectId) {
        log.trace("Got GET request on {}/{}/users", baseUrl, projectId);
        log.debug("Getting users for project with id '{}'", projectId);

        List<UserModel> users = projectService.getProjectUsers(projectId);
        log.debug("Fetched {} users", users.size());

        var response = userMapper.mapModelToListResponse(users, users.size());
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Создание проекта")
    @PostMapping
    public ProjectResponseSlim createProject(@RequestBody CreateProjectRequest request) {
        log.trace("Got POST request on {}", baseUrl);

        log.debug("Creating project '{}'", request.title());
        var response = projectMapper.toSlimResponse(projectService.createProject(projectMapper.toModel(request)));
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Удаление проекта")
    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Integer projectId) {
        log.trace("Got POST request on {}/{}", baseUrl, projectId);
        log.debug("Deleting project with id '{}'", projectId);
        projectService.deleteProject(projectId);
        log.debug("Deleted project with id '{}'", projectId);
    }

    // FIXME
    @Operation(summary = "Добавление участника в проект")
    @PutMapping("/{projectId}/users")
    public ProjectResponse addUserToProject(@PathVariable Integer projectId,
                                            @RequestBody ProjectAddUserRequest request) {
        log.trace("Got PUT request on {}/{}/users", baseUrl, projectId);

        log.debug("Adding user '{}' to project  with id '{}'", request.userId(), projectId);
        var response = projectMapper.toResponse(projectService.addMember(projectId, request.userId()));
        log.debug("User added. New user count: {}", response.userCount());
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Обновление информации о проекте")
    @PutMapping("/{projectId}")
    public ProjectResponseSlim updateProject(@PathVariable Integer projectId,
                                             @RequestBody UpdateProjectRequest request) {
        log.trace("Got PUT request on {}/{}", baseUrl, projectId);

        log.debug("Updating project '{}'", projectId);
        var project = projectService.updateProject(
                projectMapper.toModel(request, projectId));

        var response = projectMapper.toSlimResponse(project);
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Выход из проекта")
    @DeleteMapping("/{projectId}/users/{userId}")
    public void deleteUserFromProject(@PathVariable Integer projectId,
                                      @PathVariable Integer userId) {
        log.trace("Got DELETE request on {}/{}/users/{}", baseUrl, projectId, userId);
        log.debug("Deleting user with id '{}' from project with id '{}'", userId, projectId);
        projectService.removeMember(projectId, userId);
        log.debug("Deleted user with id '{}' from project with id '{}'", userId, projectId);
    }

}