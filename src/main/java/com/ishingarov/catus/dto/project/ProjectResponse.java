package com.ishingarov.catus.dto.project;

import java.time.LocalDate;

public record ProjectResponse(
        Integer id,
        String title,
        String description,
        LocalDate createdAt,
        Integer creatorId,
        Integer userCount
) {
}
