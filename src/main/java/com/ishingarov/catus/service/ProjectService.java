package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.ProjectModel;
import com.ishingarov.catus.model.domain.TaskModel;
import com.ishingarov.catus.model.domain.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    ProjectModel getProject(Integer id);

    List<ProjectModel> getProjects();

    List<TaskModel> getProjectTasks(Integer id);

    ProjectModel createProject(ProjectModel project);

    List<UserModel> getProjectUsers(Integer id);

    void deleteProject(Integer id);

    void removeMember(Integer projectId, Integer userId);

    UserModel addMember(Integer projectId, Integer userId);

    ProjectModel updateProject(ProjectModel projectEntity);
}
