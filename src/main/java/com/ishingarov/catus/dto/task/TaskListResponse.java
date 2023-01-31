package com.ishingarov.catus.dto.task;

import java.util.List;

public record TaskListResponse(
        List<TaskResponseSlim> tasks,
        Integer total
) {
}
