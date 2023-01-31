package com.ishingarov.catus.model.domain;

public record CommentModel(Integer id,
                           String content,
                           Integer userId,
                           Integer taskId) {

}
