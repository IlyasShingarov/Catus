package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.TaskStatus;

import java.time.LocalDate;

public record TaskModel(
        Integer id,
        String title,
        String description,
        UserModel author,
        Integer projectId,
        LocalDate createDttm,
        LocalDate dueDate,
        String type,
        TaskStatus taskStatus,
        Integer commentCount
) {
}
