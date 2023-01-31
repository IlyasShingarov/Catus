package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserModelMapper.class, TaskModelMapper.class})
public interface ProjectModelMapper {

    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "id", source = "model.id")
    Project toEntity(ProjectModel model, UserModel user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "users", ignore = true)
    Project toEntity(ProjectModel model, @MappingTarget Project entity);

    @Mapping(target = "creatorId", source = "entity.createdBy.id")
    ProjectModel toModel(Project entity);

    List<ProjectModel> toModel(List<Project> entity);

}
