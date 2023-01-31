package com.ishingarov.catus.dto.user;

public record UserResponseSlim(
        Integer id,
        String login,
        String name,
        String role,
        Integer groupId
) {
}
