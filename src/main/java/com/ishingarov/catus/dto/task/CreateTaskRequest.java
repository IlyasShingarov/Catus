package com.ishingarov.catus.dto.task;

import java.time.LocalDate;

public record CreateTaskRequest(
        String title,
        String description,
        String type,
        String status,
        LocalDate dueDate
) {
}
