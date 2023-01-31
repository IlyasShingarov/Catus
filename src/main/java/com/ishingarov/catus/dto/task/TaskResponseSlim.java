package com.ishingarov.catus.dto.task;

import java.time.LocalDate;

public record TaskResponseSlim(
        String title,
        String type,
        LocalDate dueDate,
        String status,
        Integer commentCount
) {
}
