package com.ishingarov.catus.dto.comment;

public record CommentResponse(
        Integer id,
        String content,
        Integer taskId,
        Integer authorId
) {
}
