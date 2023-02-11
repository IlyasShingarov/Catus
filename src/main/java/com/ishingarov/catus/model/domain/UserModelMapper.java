package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupInfoMapper.class)
public interface UserModelMapper {

    @Mapping(target = "group", source = "entity.inGroup")
    UserModel toModel(User entity);

    @Mapping(target = "managedProjects", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "inGroup", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "avatarUrl", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User toEntity(UserModel model, @MappingTarget User user);

    List<UserModel> toModel(List<User> entity);

}
