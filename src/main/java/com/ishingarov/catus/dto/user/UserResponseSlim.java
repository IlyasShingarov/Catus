package com.ishingarov.catus.dto.user;

import com.ishingarov.catus.dto.group.GroupResponseSlim;
import com.ishingarov.catus.model.domain.GroupInfo;

public record UserResponseSlim(
        Integer id,
        String login,
        String name,
        String role,
        GroupInfo group
) {
}
