package com.ishingarov.catus.controller.security;

import com.ishingarov.catus.model.UserRole;
import com.ishingarov.catus.service.ProjectService;
import com.ishingarov.catus.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class ProjectAccessProviderImpl implements ProjectAccessProvider {

    private final TokenService tokenService;
    private final ProjectService projectService;

    @Override
    public boolean checkId(Integer id) {
        var user = tokenService.getCurrentUser();
        var project = projectService.getProject(id);
        return project.creatorId().equals(user.id()) || user.role().equals(UserRole.ADMIN);
    }

    @Override
    public boolean checkIfCreateAllowed(Integer id) {
        return false;
    }

    @Override
    public boolean checkIfUpdateAllowed(Integer id) {
        return checkId(id);
    }

    @Override
    public boolean checkIfDeleteAllowed(Integer id) {
        return checkId(id);
    }

    @Override
    public boolean checkIfReadAllowed(Integer id) {
        return false;
    }
}
