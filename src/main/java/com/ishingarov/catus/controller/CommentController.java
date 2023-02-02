package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.comment.*;
import com.ishingarov.catus.service.CommentService;
import com.ishingarov.catus.service.TaskService;
import com.ishingarov.catus.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/projects/{projectId}/tasks/{taskId}/comments")
@Tag(name = "Комментарии", description = "Методы взаимодействия с коллекцией комментариев")
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;

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
        return commentMapper.toResponse(commentService.getCommentById(commentId));
    }

    @Operation(summary = "Получение списка комментариев задачи")
    @GetMapping
    public CommentListResponse getTaskComments(@PathVariable Integer taskId,
                                               @PathVariable String projectId) {
        var comments = commentService.getCommentsByTaskId(taskId);
        return commentMapper.toListResponse(comments, comments.size());
    }

    @Operation(summary = "Создание комментария к задаче")
    @PostMapping
    public CommentResponse commentTask(@PathVariable Integer taskId,
                                       @RequestBody CreateCommentRequest request) {
        var model = commentMapper.toModel(request, taskId);
        var comment = commentService.createComment(model);
        var response = commentMapper.toResponse(comment);

        log.trace("Response payload: {}", response);
        return response;
    }

}
