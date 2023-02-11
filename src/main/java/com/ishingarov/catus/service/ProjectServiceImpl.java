package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.*;
import com.ishingarov.catus.repository.ProjectRepository;
import com.ishingarov.catus.repository.TaskRepository;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final TaskRepository taskRepository;
    private final TaskModelMapper taskModelMapper;
    private final ProjectRepository projectRepository;
    private final ProjectModelMapper projectModelMapper;
    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;
    private final TokenService tokenService;

    @Override
    public ProjectModel getProject(Integer id) {
        return projectModelMapper.toModel(
                projectRepository.getById(id));
    }

    @Override
    public List<ProjectModel> getProjects() {
        return projectModelMapper.toModel(
                projectRepository.findAll());
    }

    @Override
    public ProjectModel createProject(ProjectModel project) {
        var user = tokenService.getCurrentUser();
        var entity = projectRepository.save(projectModelMapper.toEntity(project, user));
        return projectModelMapper.toModel(entity);
    }

    @Override
    public List<UserModel> getProjectUsers(Integer id) {
        return userModelMapper.toModel(
                userRepository.findAllByProjectsContaining(
                        projectRepository.getById(id)));
    }

    @Override
    public void deleteProject(Integer id) {
        var project = projectRepository.findById(id);
        if (project.isPresent()) {
            project.get().getTasks().clear();
            var users = project.get().getUsers();
            for (var user : users) {
                user.getProjects().remove(project.get());
                userRepository.save(user);
            }
            projectRepository.deleteById(id);
        }
    }

    @Override
    public void removeMember(Integer projectId, Integer userId) {
        var project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        var user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        project.getUsers().remove(user);
        user.getProjects().remove(project);
        userRepository.save(user);
        projectRepository.save(project);
    }

    @Override
    public UserModel addMember(Integer projectId, Integer userId) {
        var project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        var user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.getProjects().add(project);
        project.getUsers().add(user);
        project = projectRepository.save(project);
        user = userRepository.save(user);
        return userModelMapper.toModel(user);
    }

    @Override
    public ProjectModel updateProject(ProjectModel project) {
        var entity = projectRepository.findById(project.id())
                .orElseThrow(EntityNotFoundException::new);
        entity = projectModelMapper.toEntity(project, entity);
        return projectModelMapper.toModel(projectRepository.save(entity));
    }

    @Override
    public List<TaskModel> getProjectTasks(Integer id) {
        return taskModelMapper.toModel(
                taskRepository.findAllByProject(
                        projectRepository.getById(id)));
    }

}



