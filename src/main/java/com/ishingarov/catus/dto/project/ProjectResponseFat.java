package com.ishingarov.catus.dto.project;

import java.time.LocalDate;

public record ProjectResponseFat(
        Integer id,
        String title,
        String description,
        String status,
        LocalDate createDttm
) {
}


