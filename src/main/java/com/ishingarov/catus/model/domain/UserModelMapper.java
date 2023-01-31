package com.ishingarov.catus.model.domain;

import com.ishingarov.catus.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserModelMapper {

    @Mapping(target = "groupId", source = "entity.inGroup.id")
    UserModel mapEntityOnModel(User entity);

    List<UserModel> mapEntityOnModel(List<User> entity);

}
