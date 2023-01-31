package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.CommentModel;
import com.ishingarov.catus.model.entity.Comment;
import com.ishingarov.catus.repository.CommentRepository;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.
                findById(commentId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Comment updateComment(CommentModel comment) {
        return null;
    }


    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment createComment(CommentModel comment) {

        var entity = Comment.builder()
                .id(comment.id())
                .content(comment.content())
                .createdBy(userRepository
                        .getById(comment.userId()))
                .build();

        return commentRepository.save(entity);
    }

}
