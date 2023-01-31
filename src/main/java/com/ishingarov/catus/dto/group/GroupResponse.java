package com.ishingarov.catus.dto.group;

import com.ishingarov.catus.dto.user.UserResponseSlim;

import java.util.List;

public record GroupResponse(
        Integer id,
        String name,
        List<UserResponseSlim> users
) {
}
