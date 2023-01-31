package com.ishingarov.catus.dto.project;

public record UpdateProjectRequest(
        String title,
        String description,
        String status
) {
}
