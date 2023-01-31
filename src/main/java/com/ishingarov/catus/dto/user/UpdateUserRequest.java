package com.ishingarov.catus.dto.user;

public record UpdateUserRequest(
        String name,
        String login,
        String password,
        String role,
        Integer groupId
) {
}
