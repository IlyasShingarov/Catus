package com.ishingarov.catus.dto.group;

public record GroupResponseSlim(
        Integer id,
        String name,
        Integer userCount
) {
}
