package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.comment.*;
import com.ishingarov.catus.repository.CommentRepository;
import com.ishingarov.catus.service.CommentService;
import com.ishingarov.catus.service.TaskService;
import com.ishingarov.catus.service.TokenService;
import com.ishingarov.catus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/projects/{projectId}/tasks/{taskId}/comments")
@Tag(name = "Комментарии", description = "Методы взаимодействия с коллекцией комментариев")
public class CommentController {
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;
    private final CommentService commentService;
    private final UserService userService;

    @Operation(summary = "Изменение содержания комментария")
    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Integer commentId, @RequestBody UpdateCommentRequest request) {
        log.debug("Received PUT request on {}/{}", "api/v1/comments", commentId);
        log.trace("Request payload: {}", request);
        return null;
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId, @PathVariable String projectId) {
        log.debug("Received DELETE request on id {}", commentId);
        commentService.deleteComment(commentId);
    }

    @Operation(summary = "Получение комментария")
    @GetMapping("/{commentId}")
    public CommentResponse getComment(@PathVariable Integer commentId) {
        var comment = commentService.getCommentById(commentId);
        var author = userService.getUser(comment.userId());
        return commentMapper.toResponse(comment, author);
    }

    @Operation(summary = "Получение списка комментариев задачи")
    @GetMapping
    public CommentListResponse getTaskComments(@PathVariable Integer taskId,
                                               @PathVariable String projectId) {
        var comments = commentService.getCommentsByTaskId(taskId)
                .stream().map(
                        comment -> commentMapper
                                .toResponse(comment, userService
                                        .getUser(comment.
                                                userId()))).toList();
        return new CommentListResponse(comments, comments.size());
    }

    @Operation(summary = "Создание комментария к задаче")
    @PostMapping
    public CommentResponse commentTask(@PathVariable Integer taskId,
                                       @RequestBody CreateCommentRequest request) {
        var model = commentMapper.toModel(request, taskId);
        var comment = commentService.createComment(model);
        var author = userService.getUser(comment.userId());
        var response = commentMapper.toResponse(comment, author);

        log.trace("Response payload: {}", response);
        return response;
    }

}
