package com.makar.test.service.user.impl;

import com.makar.test.domain.Task;
import com.makar.test.domain.UserAuth;
import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;
import com.makar.test.exception.NotFoundException;
import com.makar.test.repository.TaskRepository;
import com.makar.test.service.TaskService;
import com.makar.test.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserAuthService userAuthService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserAuthService userAuthService) {
        this.taskRepository = taskRepository;
        this.userAuthService = userAuthService;
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("task not found"));
    }

    @Override
    public Task createTask(TaskRequest request) {
        Task task = new Task(request.getDescription(), request.getName());
        task.getSharedWith().add(userAuthService.getCurrentUser());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskRequest request) {
        Task task = getTask(id);
        if (!StringUtils.isEmpty(request.getName())) {
            task.setName(request.getName());
        }
        if (!StringUtils.isEmpty(request.getDescription())) {
            task.setDescription(request.getDescription());
        }
        return taskRepository.save(task);
    }

    @Override
    public void shareTask(ShareTaskRequest request) {
        UserAuth userAuth = userAuthService.findByEmail(request.getEmail());
        userAuth.getCreatedTasks().add(getTask(request.getId()));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(getTask(id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAllBySharedWithContains(userAuthService.getCurrentUser());
    }

}
