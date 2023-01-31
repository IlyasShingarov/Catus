package com.ishingarov.catus.model.domain;

import java.util.List;

public record GroupModel(
        Integer id,
        String name,
        List<UserModel> users
) {
}
