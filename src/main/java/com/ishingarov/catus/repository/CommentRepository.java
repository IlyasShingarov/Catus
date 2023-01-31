package com.ishingarov.catus.repository;

import com.ishingarov.catus.model.entity.Comment;
import com.ishingarov.catus.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByTask(Task task);

    List<Comment> findAllByTaskId(Integer taskEntityId);
}