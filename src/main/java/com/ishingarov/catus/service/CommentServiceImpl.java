package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.CommentModel;
import com.ishingarov.catus.model.domain.CommentModelMapper;
import com.ishingarov.catus.repository.CommentRepository;
import com.ishingarov.catus.repository.TaskRepository;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentModelMapper commentModelMapper;
    private final TokenService tokenService;
    private final TaskRepository taskRepository;

    @Override
    public CommentModel getCommentById(Integer commentId) {
        return commentModelMapper.toModel(
                commentRepository.findById(commentId)
                        .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<CommentModel> getCommentsByTaskId(Integer taskId) {
        return commentModelMapper.toModel(commentRepository.findAllByTaskId(taskId));
    }

    @Override
    public CommentModel updateComment(CommentModel comment) {
        return commentModelMapper.toModel(
                commentRepository.save(
                        commentModelMapper.toEntity(
                                comment, commentRepository.findById(comment.id())
                                        .orElseThrow(EntityNotFoundException::new))));
    }

//    var entity = commentRepository.findById(comment.id()).orElseThrow(EntityNotFoundException::new);
//    entity = commentModelMapper.toEntity(comment, entity);
//
//        return commentModelMapper.toModel(commentRepository.save(entity));

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentModel createComment(CommentModel comment) {
        var auth = tokenService.getCurrentUser();
        var entity = commentModelMapper.toEntity(comment, auth);
        var task = taskRepository.findById(comment.taskId()).orElseThrow(EntityNotFoundException::new);
        entity.setTask(task);
        return commentModelMapper.toModel(
                commentRepository.save(entity));
    }

}
