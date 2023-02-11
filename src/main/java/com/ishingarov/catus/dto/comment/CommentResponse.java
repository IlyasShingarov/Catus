package com.ishingarov.catus.dto.comment;

import com.ishingarov.catus.dto.user.UserResponseSlim;

public record CommentResponse(
        Integer id,
        String content,
        Integer taskId,
        UserResponseSlim author
) {
}
