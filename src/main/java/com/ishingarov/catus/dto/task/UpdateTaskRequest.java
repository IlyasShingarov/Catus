package com.ishingarov.catus.dto.task;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        String status,
        String type,
        LocalDate dueDate
) {
}
