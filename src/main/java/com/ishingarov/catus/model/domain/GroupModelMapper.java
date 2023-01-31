package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserModelMapper.class})
public interface GroupModelMapper {
    GroupModel toModel(Group entity);

    @Mapping(target = "userCount", expression = "java(entity.getUsers() != null ? entity.getUsers().size() : 0)")
    GroupModelSlim toSlimModel(Group entity);

    List<GroupModelSlim> toSlimModel(List<Group> entity);

    @Mapping(target = "users", ignore = true)
    Group toEntity(GroupModelSlim model);

    @Mapping(target = "users", ignore = true)
    Group toEntity(GroupModelSlim model, @MappingTarget Group entity);

}
