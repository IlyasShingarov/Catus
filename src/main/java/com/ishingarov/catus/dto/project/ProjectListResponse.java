package com.ishingarov.catus.dto.project;

import java.util.List;

public record ProjectListResponse(
        List<ProjectResponseSlim> projects,
        Integer total
) {
}
