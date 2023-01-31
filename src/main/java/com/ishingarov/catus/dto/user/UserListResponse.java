package com.ishingarov.catus.dto.user;

import java.util.List;

public record UserListResponse(
        List<UserResponseSlim> users,
        Integer total
) {
}
