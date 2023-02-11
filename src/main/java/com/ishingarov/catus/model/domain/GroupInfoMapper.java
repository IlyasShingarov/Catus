package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupInfoMapper {
    GroupInfo toInfo(Group entity);
}
