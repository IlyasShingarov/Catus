package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.TaskModel;
import com.ishingarov.catus.model.domain.TaskModelMapper;
import com.ishingarov.catus.repository.ProjectRepository;
import com.ishingarov.catus.repository.TaskRepository;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskModelMapper taskModelMapper;
    private final TokenService tokenService;


    @Override
    public TaskModel getTask(Integer taskId) {
        return taskModelMapper.toModel(
                taskRepository.findById(taskId)
                        .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public TaskModel createTask(TaskModel taskModel) {
        var task = taskModelMapper.toEntity(taskModel);
        var user = userRepository.getById(
                tokenService.getCurrentUser().id());
        var project = projectRepository.getById(taskModel.projectId());

        task.setProject(project);
        task.setCreatedBy(user);
        return taskModelMapper.toModel(
                taskRepository.save(task));
    }

    @Override
    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskModel changeTaskInfo(TaskModel model) {
        var task = taskRepository.findById(model.id())
                .orElseThrow(EntityNotFoundException::new);
        task = taskModelMapper.toEntity(model, task);
        task = taskRepository.save(task);
        return taskModelMapper.toModel(task);
    }

    @Override
    public TaskModel changeTaskStatus(TaskModel model) {
        return taskModelMapper.toModel(taskRepository.save(taskModelMapper.toEntity(model)));
    }


}

//    // TODO Test
//    @Override
//    public void deleteComments(Integer taskId) {
//        var task = taskRepository.findById(taskId)
//                .orElseThrow(EntityNotFoundException::new);
//        task.getComments().clear();
//        taskRepository.save(task);
//    }

//    @Override
//    public List<Comment> getComments(Integer id) {
//        return commentRepository.findByTaskId(id);
//    }