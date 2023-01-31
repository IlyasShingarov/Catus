//package com.ishingarov.catus.controller;
//
//import com.ishingarov.catus.dto.comment.CommentListResponse;
//import com.ishingarov.catus.dto.comment.CommentResponse;
//import com.ishingarov.catus.dto.comment.CreateCommentRequest;
//import com.ishingarov.catus.dto.comment.UpdateCommentRequest;
//import com.ishingarov.catus.service.CommentService;
//import com.ishingarov.catus.service.TaskService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/projects/{projectId}/tasks/{taskId}/comments")
//@Tag(name = "Комментарии", description = "Методы взаимодействия с коллекцией комментариев")
//public class CommentController {
//
////    private final CommentService commentService;
////    private final TaskService taskService;
////    private final CommentMapper commentMapper;
//
//
//
//    @Operation(summary = "Изменение содержания комментария")
//    @PutMapping("/{commentId}")
//    public CommentResponse updateComment(@PathVariable Integer commentId, @RequestBody UpdateCommentRequest request) {
//        log.debug("Received PUT request on {}/{}", "api/v1/comments", commentId);
//        log.trace("Request payload: {}", request);
////        try {
////            return commentMapper.mapCommentToResponse(
////                    commentService.updateComment(
////                        commentMapper.mapUpdateRequest(request, commentId)
////                    )
////            );
////        } catch (Exception e) {
////            log.error("An error occurred on PUT comment operation", e);
////            throw new ProccessingErrorException("Error deleting comment with id " + commentId);
////        }
//        return null;
//    }
//
//    @Operation(summary = "Удаление комментария")
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable Integer commentId) {
//        log.debug("Received DELETE request on {}/{}", "api/v1/comments", commentId);
////        try {
////            commentService.deleteComment(commentId);
////        } catch (Exception e) {
////            log.error("An error occurred on DELETE comment operation", e);
////        }
//    }
//
//    @Operation(summary = "Получение комментария")
//    @GetMapping("/{commentId}")
//    public CommentResponse getComment(@PathVariable Integer commentId) {
////        try {
////            return commentMapper.mapCommentToResponse(
////                    commentService.getComment(commentId)
////            );
////        } catch (Exception e) {
////            log.error("Error occurred on GET comment operation");
////            throw new EntityNotFoundException("Could not find Comment with id" + commentId);
////        }
//        return null;
//    }
//
//    @Operation(summary = "Получение списка комментариев задачи")
//    @GetMapping
//    public CommentListResponse getTaskComments(@PathVariable Integer taskId) {
////        List<CommentResponse> commentList = commentMapper
////                .mapCommentToResponse(taskService.getComments(taskId));
////        return new CommentListResponse(commentList, commentList.size());
//        return null;
//    }
//
//    @Operation(summary = "Создание комментария к задаче")
//    @PostMapping
//    public CommentResponse commentTask(@PathVariable Integer taskId,
//                                       @RequestBody CreateCommentRequest request
//    ) {
////        Comment comment = commentMapper.mapCreateRequest(request);
////        return commentMapper.mapCommentToResponse(
////                commentService.createComment(comment, taskId)
////        );
//        return null;
//    }
//
//}
