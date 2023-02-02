package com.ishingarov.catus.dto.group;

import com.ishingarov.catus.model.domain.GroupModel;
import com.ishingarov.catus.model.domain.GroupModelSlim;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupResponseSlim mapModelOnResponse(GroupModelSlim model);

    List<GroupResponseSlim> mapModelOnResponse(List<GroupModelSlim> model);

    GroupListResponse mapModelOnListResponse(List<GroupModelSlim> groups, Integer total);

    GroupResponse mapModelOnResponse(GroupModel model);

    @Mapping(target = "userCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    GroupModelSlim mapRequestOnSlimModel(CreateGroupRequest request);

    @Mapping(target = "userCount", ignore = true)
    @Mapping(target = "id", source = "groupId")
    GroupModelSlim mapRequestOnSlimModel(Integer groupId, UpdateGroupRequest request);

}
