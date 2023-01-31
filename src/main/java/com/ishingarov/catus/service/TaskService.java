package com.ishingarov.catus.service;

import com.ishingarov.catus.model.domain.TaskModel;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    TaskModel getTask(Integer taskId);

    TaskModel createTask(TaskModel taskModel);

    void deleteTask(Integer taskId);

}
