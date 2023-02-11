package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.ProjectModel;
import com.ishingarov.catus.model.domain.UserModel;
import com.ishingarov.catus.model.entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {

    UserModel createUser(UserModel userModel);

    UserModel updateGroup(UserModel userModel);

    UserModel updateUser(UserModel userModel);

    List<UserModel> getUsers();

    UserModel getUser(Integer id);

    @Transactional
    void deleteUser(Integer id);

    List<ProjectModel> getUserProjects(Integer id);

    List<ProjectModel> getManagedProjects(Integer id);

    Project createProject(Project project);

    UserModel getUserByLogin(String login);
}
