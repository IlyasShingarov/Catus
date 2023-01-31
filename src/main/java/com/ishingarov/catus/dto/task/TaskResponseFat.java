package com.ishingarov.catus.dto.task;

import com.ishingarov.catus.dto.user.UserResponseSlim;

public record TaskResponseFat(
        String title,
        String description,
        UserResponseSlim creator
) {
}
