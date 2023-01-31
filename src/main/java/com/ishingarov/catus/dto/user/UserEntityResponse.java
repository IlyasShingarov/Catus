package com.ishingarov.catus.dto.user;

public record UserEntityResponse(
        Integer id,
        String login,
        String name,
        String title,
        String password
) {
}
