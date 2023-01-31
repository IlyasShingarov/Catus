package com.ishingarov.catus.dto.user;

// TODO Review

import javax.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull
        String login,
        String name,
        String password,
        String role
) {
}
