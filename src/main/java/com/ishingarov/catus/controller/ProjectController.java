package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.project.*;
import com.ishingarov.catus.dto.user.UserListResponse;
import com.ishingarov.catus.dto.user.UserMapper;
import com.ishingarov.catus.dto.user.UserResponseSlim;
import com.ishingarov.catus.model.domain.UserModel;
import com.ishingarov.catus.service.ProjectService;
import com.ishingarov.catus.service.TokenService;
import com.ishingarov.catus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Проекты", description = "Методы взаимодействия с коллекцией проектов")
public class ProjectController {
    private final static String baseUrl = "/api/v1/projects/";
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final UserService userService;

    // TODO Сделать так, чтобы получались только те проекты, которые доступны для авторизованного пользователя
    @Operation(summary = "Получение всех проектов")
    @GetMapping
    public ProjectListResponse getProjects() {
        log.trace("Got GET request on {}", baseUrl);
        var user = tokenService.getCurrentUser();
        var projects = switch (user.role()) {
            case ADMIN -> projectService.getProjects();
            case TEACHER -> userService.getManagedProjects(user.id());
            case STUDENT -> userService.getUserProjects(user.id());
        };

        var response = projectMapper.toListResponse(projects, projects.size());
        log.trace("Response payload: {}", response);
        return response;
    }

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
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    public ProjectResponseSlim createProject(@RequestBody CreateProjectRequest request) {
        log.trace("Got POST request on {}", baseUrl);

        log.debug("Creating project '{}'", request.title());
        var response = projectMapper.toSlimResponse(
                projectService.createProject(
                        projectMapper.toModel(request)));
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Удаление проекта")
    @DeleteMapping("/{projectId}")
    @PreAuthorize("@projectAccessProviderImpl.checkIfDeleteAllowed(#projectId)")
    public void deleteProject(@PathVariable Integer projectId) {
        log.trace("Got POST request on {}/{}", baseUrl, projectId);
        log.debug("Deleting project with id '{}'", projectId);
        projectService.deleteProject(projectId);
        log.debug("Deleted project with id '{}'", projectId);
    }

    // FIXME
    @Operation(summary = "Добавление участника в проект")
    @PutMapping("/{projectId}/users")
    @PreAuthorize("@projectAccessProviderImpl.checkIfUpdateAllowed(#projectId)")
    public UserResponseSlim addUserToProject(@PathVariable Integer projectId,
                                             @RequestBody ProjectAddUserRequest request) {
        log.trace("Got PUT request on {}/{}/users", baseUrl, projectId);

        log.debug("Adding user '{}' to project  with id '{}'", request.userId(), projectId);
        var response = userMapper.mapModelToSlimResponse(projectService.addMember(projectId, request.userId()));
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Обновление информации о проекте")
    @PutMapping("/{projectId}")
    @PreAuthorize("@projectAccessProviderImpl.checkIfUpdateAllowed(#projectId)")
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
    @DeleteMapping("/{projectId}/users/0")
    public void deleteUserFromProject(@PathVariable Integer projectId) {
        log.trace("Got DELETE request on {}/{}/users/0", baseUrl, projectId);
        var userId = tokenService.getCurrentUser().id();
        log.debug("Deleting user with id '{}' from project with id '{}'", userId, projectId);
        projectService.removeMember(projectId, userId);
        log.debug("Deleted user with id '{}' from project with id '{}'", userId, projectId);
    }


    @Operation(summary = "Выход из проекта")
    @DeleteMapping("/{projectId}/users/{userId}")
    @PreAuthorize("@projectAccessProviderImpl.checkIfUpdateAllowed(#projectId)")
    public void deleteUserFromProject(@PathVariable Integer projectId,
                                      @PathVariable @Min(1) Integer userId) {
        log.trace("Got DELETE request on {}/{}/users/{}", baseUrl, projectId, userId);
        log.debug("Deleting user with id '{}' from project with id '{}'", userId, projectId);
        projectService.removeMember(projectId, userId);
        log.debug("Deleted user with id '{}' from project with id '{}'", userId, projectId);
    }

}
