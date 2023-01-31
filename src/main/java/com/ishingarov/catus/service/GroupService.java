package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.GroupModel;
import com.ishingarov.catus.model.domain.GroupModelSlim;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    GroupModel getGroupById(Integer id);

    List<GroupModelSlim> getGroups();

    GroupModelSlim createGroup(GroupModelSlim groupEntity);

    GroupModelSlim updateGroup(GroupModelSlim groupEntity);

    void deleteGroup(Integer id);

//    GroupModelSlim removeUser(Integer groupId, Integer userId);
//    GroupModelSlim addUserToGroup(Integer groupId, Integer userId);

}
