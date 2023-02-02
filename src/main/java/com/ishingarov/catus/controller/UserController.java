package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.project.ProjectListResponse;
import com.ishingarov.catus.dto.project.ProjectMapper;
import com.ishingarov.catus.dto.user.*;
import com.ishingarov.catus.exception.DeletionErrorException;
import com.ishingarov.catus.exception.UserNotFoundException;
import com.ishingarov.catus.service.TokenService;
import com.ishingarov.catus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Пользователи", description = "Методы взаимодействия с коллекцией пользователей")
public class UserController {
    private final static String baseUrl = "/api/v1/users";
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProjectMapper projectMapper;
    private final TokenService tokenService;

    @Operation(summary = "Получение всех пользователей")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public UserListResponse getUsers() {
        log.trace("Got GET request on {}", "/api/v1/users");
        var users = userService.getUsers();
        var response = userMapper.mapModelToListResponse(users, users.size());
        log.trace("Response payload: {}", response);
        return response;
    }

    @Operation(summary = "Получение пользователя")
    @GetMapping("/{userId}")
    public UserResponseSlim getUser(@PathVariable Integer userId) {
        log.trace("Got GET request on {}/{}", "/api/v1/users", userId);
        if (userId == 0) return userMapper.mapModelToSlimResponse(tokenService.getCurrentUser());

        var user = userService.getUser(userId);
        var response = userMapper.mapModelToSlimResponse(user);

        log.trace("Response payload: {}", response);
        return response;
    }

    @Operation(summary = "Получение всех проектов доступных пользователю")
    @GetMapping("/{userId}/projects")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ProjectListResponse getUserProjects(@PathVariable Integer userId) {
        log.trace("Got GET request on {}/{}/{}", baseUrl, userId, "projects");
        var projects = userService.getUserProjects(userId);
        return projectMapper.toListResponse(projects, projects.size());
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public UserResponseSlim createUser(@RequestBody CreateUserRequest request) {
        log.trace("Got POST request on {}", baseUrl);
        log.trace("Request payload: {}", request);
        var model = userMapper.mapRequestOnModel(request);
        var user = userService.createUser(model);
        var response = userMapper.mapModelToSlimResponse(user);
        log.trace("Sending response: {}", response);
        return response;
    }

    @Operation(summary = "Изменить группу")
    @PatchMapping("/{userId}/groups")
    @PreAuthorize("hasAnyAuthority('SCOPE_TEACHER', 'SCOPE_ADMIN')")
    public UserResponseSlim patchUser(@PathVariable Integer userId, @RequestBody ChangeGroupRequest request) {
        log.trace("Got PATCH request on {}/{}", baseUrl, userId);
        log.trace("Request payload: {}", request);
        var model = userMapper.mapRequestOnModel(request, userId);
        var response = userMapper.mapModelToSlimResponse(userService.updateGroup(model));
        log.trace("Response payload: {}", response);
        return response;
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping("/{userId}")
    @PreAuthorize("@userAccessProvider.checkIfUpdateAllowed(#userId)")
    public UserResponseSlim updateUser(@PathVariable Integer userId, @RequestBody UpdateUserRequest request) {
        log.trace("Got PUT request on {}/{}", baseUrl, userId);
        var user = userMapper.mapRequestOnModel(userId, request);
        user = userService.updateUser(user);
        var response = userMapper.mapModelToSlimResponse(user);
        log.trace("Response payload: {}", response);
        return response;
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@userAccessProvider.checkIfDeleteAllowed(#userId)")
    public void deleteUser(@PathVariable Integer userId) {
        log.trace("Got DELETE request on {}/{}", baseUrl, userId);
        try {
            userService.deleteUser(userId);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Attempted to delete non existing user");
            throw new UserNotFoundException("User not found on DELETE operation");
        } catch (Exception e) {
            log.warn("User deletion failed");
            throw new DeletionErrorException("Error occured on DELETE operation");
        }
    }

}

// Убрано в угоду сроков проекта
// Хуй его знает что это вообще было
// Пользователь получает все проекты потом обращается по айдишнику
// Пользователь проектом не владеет
//    @GetMapping("/{userId}/projects/{projectId}")
//    public ProjectEntityResponse getUserProject(@PathVariable Integer userId,
//                                                @PathVariable Integer projectId) {
//        log.trace("Got GET request on {}/{}/{}/{}",  "/api/v1/users/", userId, "projects", projectId);
//        return userServiceImpl.getUserProject(projectId, userId);
//    }