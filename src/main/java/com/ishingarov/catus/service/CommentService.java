package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.CommentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentModel getCommentById(Integer commentId);

    List<CommentModel> getCommentsByTaskId(Integer taskId);

    CommentModel updateComment(CommentModel comment);

    void deleteComment(Integer id);

    CommentModel createComment(CommentModel comment);
}
