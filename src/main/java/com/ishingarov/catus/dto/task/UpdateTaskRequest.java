package com.ishingarov.catus.dto.task;

public record UpdateTaskRequest(
        String title,
        String description
) {
}
