package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.ProjectModel;
import com.ishingarov.catus.model.domain.ProjectModelMapper;
import com.ishingarov.catus.model.domain.UserModel;
import com.ishingarov.catus.model.domain.UserModelMapper;
import com.ishingarov.catus.model.entity.Project;
import com.ishingarov.catus.model.entity.User;
import com.ishingarov.catus.repository.GroupRepository;
import com.ishingarov.catus.repository.ProjectRepository;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //
    private final UserModelMapper userModelMapper;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ProjectRepository projectRepository;
    private final ProjectModelMapper projectModelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel createUser(UserModel userModelIn) {
        var userEntity = User.builder()
                .login(userModelIn.login())
                .name(userModelIn.name())
                .password(passwordEncoder.encode(userModelIn.password()))
                .role(userModelIn.role())
                .isActive(true)
                .build();

        var user = userRepository.save(userEntity);
        return userModelMapper.toModel(user);
    }

    @Override
    public UserModel updateGroup(UserModel userModel) {
        var entity = userRepository.findById(userModel.id())
                .orElseThrow(EntityNotFoundException::new);
        if (userModel.group().id() != null) {
            var group = groupRepository.getById(userModel.group().id());
            entity.setInGroup(group);
        } else {
            entity.setInGroup(null);
        }
        entity = userRepository.save(entity);
        return userModelMapper.toModel(entity);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        var user = userRepository.findById(userModel.id())
                .orElseThrow(EntityNotFoundException::new);
        user = userModelMapper.toEntity(userModel, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userModelMapper.toModel(user);
    }

    @Override
    public List<UserModel> getUsers() {
        var users = userRepository.findAll();
        return userModelMapper.toModel(users);
    }

    @Override
    public UserModel getUser(Integer id) {
        var user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return userModelMapper.toModel(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<ProjectModel> getUserProjects(Integer id) {
        var userRef = userRepository.getById(id);
        return projectModelMapper.toModel(
                projectRepository.findAllByUsersContaining(userRef));
    }

    @Override
    public List<ProjectModel> getManagedProjects(Integer id) {
        var user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return projectModelMapper.toModel(
                user.getManagedProjects().stream().toList());
    }

    @Override
    public Project createProject(Project project) {
        return null;
    }

    @Override
    public UserModel getUserByLogin(String login) {
        return userModelMapper.toModel(userRepository.findByLogin(login)
                .orElseThrow(EntityNotFoundException::new));
    }
}
