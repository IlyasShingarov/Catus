package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentModelMapper {
    @Mapping(target = "userId", source = "createdBy.id")
    @Mapping(target = "taskId", source = "task.id")
    CommentModel toModel(Comment comment);

    List<CommentModel> toModel(List<Comment> comment);

    @Mapping(target = "task", ignore = true)
    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "id", source = "model.id")
    Comment toEntity(CommentModel model, UserModel user);

    @Mapping(target = "task", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Comment toEntity(CommentModel model, @MappingTarget Comment comment);

}
