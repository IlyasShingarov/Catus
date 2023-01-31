//package com.ishingarov.catus.dto.comment;
//
//import com.ishingarov.catus.model.Comment;
//import com.ishingarov.catus.model.entity.Comment;
//import org.mapstruct.BeanMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface CommentMapper {
//
//    @Mapping(target = "author", source = "entity.creator")
//    @Mapping(target = "taskId", source = "entity.taskEntity.id")
//    @Mapping(target = "id", source = "entity.id")
//    CommentResponse mapCommentToResponse(Comment entity);
//
//    List<CommentResponse> mapCommentToResponse(List<Comment> entity);
//
//    @Mapping(target = "content", source = "request.content")
//    Comment mapCreateRequest(CreateCommentRequest request);
//
//    @Mapping(target = "taskEntity", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "content", source = "request.content")
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    Comment mapUpdateRequest(UpdateCommentRequest request, Integer id);
//
//}
