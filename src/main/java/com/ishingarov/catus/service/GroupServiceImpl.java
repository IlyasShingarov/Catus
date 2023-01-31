package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.GroupModel;
import com.ishingarov.catus.model.domain.GroupModelMapper;
import com.ishingarov.catus.model.domain.GroupModelSlim;
import com.ishingarov.catus.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupModelMapper groupModelMapper;

    @Override
    public GroupModel getGroupById(Integer id) {
        var entity = groupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return groupModelMapper.toModel(entity);
    }

    @Override
    public List<GroupModelSlim> getGroups() {
        var groups = groupRepository.findAll();
        return groupModelMapper.toSlimModel(groups);
    }

    @Override
    public GroupModelSlim createGroup(GroupModelSlim group) {
        var entity = groupModelMapper.toEntity(group);
        entity = groupRepository.saveAndFlush(entity);
        return groupModelMapper.toSlimModel(entity);
    }

    @Override
    public GroupModelSlim updateGroup(GroupModelSlim group) {
        var entity = groupRepository.findById(group.id())
                .orElseThrow(EntityNotFoundException::new);

        entity = groupModelMapper.toEntity(group, entity);
        return groupModelMapper.toSlimModel(entity);
    }

    @Override
    public void deleteGroup(Integer id) {
        var entity = groupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        entity.getUsers().forEach(user -> user.setInGroup(null));
        groupRepository.deleteById(id);
    }

//    // Бля ну под большим вопросом
//    //  Надо посмотерть на сторону репозитория
//    @Override
//    public GroupModelSlim addUserToGroup(Integer groupId, Integer userId) {
////        GroupModel foundGroup = groupRepository.findById(groupId);
////        User user = userRepository.getById(userId);
////        foundGroup.getUsers().add(user);
////        return groupRepository.save(foundGroup);
//        return null;
//    }

//    // По сути убрали пользователя из группы и сохранили новую группу,
//    //  надо смотреть в репозитории
//    @Override
//    public GroupModelSlim removeUser(Integer groupId, Integer userId) {
////        GroupModel group = groupRepository.findById(groupId);
////
////        List<User> users = group.getUsers();
////        users.remove(userRepository.getById(userId));
////        return groupRepository.save(group);
//        return null;
//    }

}
