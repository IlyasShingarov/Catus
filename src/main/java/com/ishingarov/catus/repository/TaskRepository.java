package com.ishingarov.catus.repository;

import com.ishingarov.catus.model.entity.Project;
import com.ishingarov.catus.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByProject(Project project);
}