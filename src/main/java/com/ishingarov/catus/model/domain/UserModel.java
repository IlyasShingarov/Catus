package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.UserRole;

public record UserModel(Integer id,
                        String login,
                        String name,
                        UserRole role,
                        String password,
                        GroupInfo group
) {
}
