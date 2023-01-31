package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.CommentModel;
import com.ishingarov.catus.model.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment getCommentById(Integer commentId);

    Comment updateComment(CommentModel comment);

    void deleteComment(Integer id);

    Comment createComment(CommentModel comment);
}
