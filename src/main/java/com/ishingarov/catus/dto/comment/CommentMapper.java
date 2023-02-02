package com.ishingarov.catus.dto.comment;

import com.ishingarov.catus.model.domain.CommentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "taskId", source = "taskId")
    CommentModel toModel(CreateCommentRequest commentRequest, Integer taskId);

    @Mapping(target = "authorId", source = "model.userId")
    CommentResponse toResponse(CommentModel model);

    List<CommentResponse> toResponse(List<CommentModel> models);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "comments", source = "models")
    CommentListResponse toListResponse(List<CommentModel> models, Integer total);

}
