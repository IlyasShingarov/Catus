package com.ishingarov.catus.dto.comment;

import java.util.List;

public record CommentListResponse(
        List<CommentResponse> comments,
        Integer total
) {
}
