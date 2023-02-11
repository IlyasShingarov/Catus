package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.group.*;
import com.ishingarov.catus.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Группы", description = "Методы взаимодействия с коллекцией групп")
public class GroupController {
    private final GroupMapper groupMapper;
    private final GroupService groupService;
    private final String baseUrl = "/api/v1/groups";

    @Operation(summary = "Получение списка всех групп")
    @GetMapping
    public GroupListResponse getGroups() {
        log.trace("Got GET request on {}", baseUrl);

        var groups = groupService.getGroups();
        log.debug("Fetched {} groups", groups.size());

        var response = groupMapper.toListResponse(groups, groups.size());
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Получение группы")
    @GetMapping("/{groupId}")
    public GroupResponse getGroup(@PathVariable @NotNull @Min(0) Integer groupId) {
        log.trace("Got GET request on {}/{}", baseUrl, groupId);

        var group = groupService.getGroupById(groupId);
        log.trace("Fetched group: {}", group);

        var response = groupMapper.mapModelOnResponse(group);
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Создание группы")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    public GroupResponseSlim createGroup(@Valid @RequestBody CreateGroupRequest request) {
        log.trace("Got POST request on {}", baseUrl);
        log.trace("Request payload: {}", request);
        var model = groupMapper.toSlimModel(request);

        log.debug("Creating group '{}'", request.name());
        model = groupService.createGroup(model);

        var response = groupMapper.mapModelOnResponse(model);
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Изменение данных о группе")
    @PutMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    public GroupResponseSlim updateGroup(@PathVariable Integer groupId, @RequestBody UpdateGroupRequest request) {
        log.trace("Got POST request on {}", baseUrl);
        var model = groupMapper.toSlimModel(groupId, request);

        log.debug("Changing group '{}' info", groupId);
        model = groupService.updateGroup(model);

        var response = groupMapper.mapModelOnResponse(model);
        log.trace("Response payload: {}", response);

        return response;
    }

    @Operation(summary = "Удаление группы")
    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TEACHER')")
    public void deleteGroup(@PathVariable Integer groupId) {
        log.trace("Got POST request on {}", baseUrl);
        log.debug("Deleting group: '{}'", groupId);
        groupService.deleteGroup(groupId);
        log.debug("Group '{}' deleted", groupId);
    }

}