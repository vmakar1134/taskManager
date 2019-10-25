package com.makar.test.repository;

import com.makar.test.domain.Task;
import com.makar.test.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllBySharedWithContains(UserAuth userAuth);

}
