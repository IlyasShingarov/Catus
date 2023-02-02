package com.ishingarov.catus.dto.task;

import java.time.LocalDate;

public record TaskResponseSlim(
        Integer id,
        String title,
        String type,
        LocalDate dueDate,
        String status,
        Integer commentCount
) {
}
