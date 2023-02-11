package com.ishingarov.catus.dto.comment;

import com.ishingarov.catus.dto.user.UserMapper;
import com.ishingarov.catus.model.domain.CommentModel;
import com.ishingarov.catus.model.domain.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "taskId", source = "taskId")
    CommentModel toModel(CreateCommentRequest commentRequest, Integer taskId);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "id", source = "model.id")
    CommentResponse toResponse(CommentModel model, UserModel author);

    List<CommentResponse> toResponse(List<CommentModel> models);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "comments", source = "models")
    CommentListResponse toListResponse(List<CommentModel> models, Integer total);

}
