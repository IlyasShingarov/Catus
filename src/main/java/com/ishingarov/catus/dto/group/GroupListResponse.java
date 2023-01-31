package com.ishingarov.catus.dto.group;

import java.util.List;

public record GroupListResponse(
        List<GroupResponseSlim> groups,
        Integer total
) {
}
