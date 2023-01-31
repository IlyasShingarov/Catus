package com.ishingarov.catus.repository;

import com.ishingarov.catus.model.entity.Project;
import com.ishingarov.catus.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    List<User> findAllByProjectsContaining(Project project);
}
