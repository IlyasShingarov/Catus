package com.ishingarov.catus.model.domain;

import java.time.LocalDate;
import java.util.List;

public record ProjectModel(
        Integer id,
        String title,
        String description,
        String status,
        LocalDate createDttm,
        Integer creatorId,
        List<UserModel> users,
        List<TaskModel> tasks
) {
}
