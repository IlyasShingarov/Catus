package com.ishingarov.catus.dto.user;

import com.ishingarov.catus.dto.RegisterRequestDto;
import com.ishingarov.catus.dto.group.GroupMapper;
import com.ishingarov.catus.model.domain.GroupModelSlim;
import com.ishingarov.catus.model.domain.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface UserMapper {
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    User patchUser(CreateUserRequest dto, @MappingTarget User entity);
//
//    User updateUser(CreateUserRequest dto, @MappingTarget User entity);
//
//    UserEntityResponse mapUserToResponse(User entity);
//
//    User mapCreateDtoToUser(CreateUserRequest request);
//
//    @Mapping(target = "groupId", source = "inGroupEntity.id")
//    @Mapping(target = "title", source = "role")
//    @Mapping(target = "id", source = "entity.id")
//    @Mapping(target = "name", source = "entity.name")
//    UserResponseSlim mapUserResponse(User entity);

    //    List<UserResponseSlim> mapUserResponse(Set<User> userEntities);
//
//    List<UserResponseSlim> mapUserResponse(List<User> userEntities);
    @Mapping(target = "id", source = "userId")
    UserModel mapRequestOnModel(Integer userId, UpdateUserRequest request);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserModel mapRequestOnModel(CreateUserRequest request);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserModel mapRequestOnModel(RegisterRequestDto request);

    @Mapping(target = "group", expression = "java(new GroupInfo(request.groupId(), null))")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Mapping(target = "id", source = "userId")
    UserModel mapRequestOnModel(ChangeGroupRequest request, Integer userId);

    UserResponseSlim mapModelToSlimResponse(UserModel model);

    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "name", source = "model.name")
    UserResponseSlim mapModelToSlimResponse(UserModel model, GroupModelSlim group);

    List<UserResponseSlim> mapModelToSlimResponse(List<UserModel> model);

    @Mapping(target = "users", source = "models")
    UserListResponse mapModelToListResponse(List<UserModel> models, Integer total);


}
