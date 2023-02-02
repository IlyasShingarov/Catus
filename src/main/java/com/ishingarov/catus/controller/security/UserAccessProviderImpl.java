package com.ishingarov.catus.controller.security;

import com.ishingarov.catus.model.UserRole;
import com.ishingarov.catus.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccessProviderImpl implements UserAccessProvider {

    private final TokenService tokenService;

    @Override
    public boolean checkId(Integer id) {
        var user = tokenService.getCurrentUser();
        return user.id().equals(id) || user.role().equals(UserRole.ADMIN);
    }

    @Override
    public boolean checkIfCreateAllowed(Integer id) {
        var user = tokenService.getCurrentUser();
        return user.role().equals(UserRole.ADMIN);
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
