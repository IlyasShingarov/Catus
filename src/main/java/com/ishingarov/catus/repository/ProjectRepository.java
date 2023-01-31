package com.ishingarov.catus.repository;

import com.ishingarov.catus.model.entity.Project;
import com.ishingarov.catus.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUsersContaining(User user);

}
