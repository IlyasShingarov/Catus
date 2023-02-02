package com.ishingarov.catus.dto.task;

import com.ishingarov.catus.dto.user.UserResponseSlim;

import java.time.LocalDate;

public record TaskResponseFat(
        Integer id,
        String title,
        String description,
        String status,
        String type,
        LocalDate dueDate,
        LocalDate createDttm,
        UserResponseSlim creator
) {
}
