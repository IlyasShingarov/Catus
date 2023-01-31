package com.ishingarov.catus.dto.project;

// TODO
// Add validation

import java.time.LocalDate;

public record ProjectResponseSlim(
        Integer id,
        String title,
        String description,
        String status,
        Integer creatorId,
        Integer userCount,
        Integer taskCount,
        LocalDate createdAt
) {
}
