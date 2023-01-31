package com.ishingarov.catus.dto.project;

import java.time.LocalDate;

// TODO
// Add validation

public record ProjectEntityResponse(
        Integer id,
        String title,
        String description,
        String status,
        LocalDate createDttm,
        Integer userCount
) {

}
