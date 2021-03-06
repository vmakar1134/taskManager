package com.makar.test.service.impl;

import com.makar.test.domain.Task;
import com.makar.test.domain.UserAuth;
import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;
import com.makar.test.domain.response.TaskDto;
import com.makar.test.exception.ConflictException;
import com.makar.test.exception.NotFoundException;
import com.makar.test.mapper.TaskMapper;
import com.makar.test.repository.TaskRepository;
import com.makar.test.repository.UserAuthRepository;
import com.makar.test.service.TaskService;
import com.makar.test.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserAuthRepository userAuthRepository;

    private final UserAuthService userAuthService;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserAuthRepository userAuthRepository, UserAuthService userAuthService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userAuthRepository = userAuthRepository;
        this.userAuthService = userAuthService;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDto getTaskDto(Long id) {
        return taskMapper.toDto(getTask(id));
    }

    @Override
    public TaskDto createTask(TaskRequest request) {
        if (taskExistsByName(request)) {
            throw new ConflictException("task with this name already exists");
        }
        Task task = setTask(new Task(), request);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto updateTask(Long id, TaskRequest request) {
        Task task = setTask(getTask(id), request);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void shareTask(ShareTaskRequest request) {
        UserAuth userAuth = userAuthService.findByEmail(request.getEmail());
        if (userAuth.equals(userAuthService.getCurrentUser())) {
            throw new ConflictException("Cannot share task to yourself");
        }
        Task task = getAllTasks()
                .stream()
                .filter(t -> t.getId().equals(request.getId()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("task not found"));
        userAuth.getSharedTasks().add(task);
        userAuthRepository.save(userAuth);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(getTask(id));
    }

    @Override
    public List<TaskDto> getAllTaskDtos() {
        return taskMapper.toDtoList(getAllTasks());
    }

    @Override
    public List<TaskDto> getCreated() {
        return taskMapper.toDtoList(userAuthService.getCurrentUser().getCreatedTasks());
    }

    @Override
    public List<TaskDto> getShared() {
        return taskMapper.toDtoList(new ArrayList<>(userAuthService.getCurrentUser().getSharedTasks()));
    }

    private List<Task> getAllTasks() {
        UserAuth currentUser = userAuthService.getCurrentUser();
        List<Task> createdTasks = currentUser.getCreatedTasks();
        Set<Task> sharedTasks = currentUser.getSharedTasks();
        sharedTasks.addAll(createdTasks);
        return new ArrayList<>(sharedTasks);
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("task not found by id"));
    }

    private Task setTask(Task task, TaskRequest request) {
        if (!StringUtils.isEmpty(request.getName())) {
            task.setName(request.getName());
        }
        if (!StringUtils.isEmpty(request.getDescription())) {
            task.setDescription(request.getDescription());
        }
        if (request.getDeadline() != null) {
            task.setDeadline(request.getDeadline());
        }
        if (task.getCreatedBy() == null) {
            task.setCreatedBy(userAuthService.getCurrentUser());
        }
        return task;
    }

    private boolean taskExistsByName(TaskRequest request) {
        return userAuthService.getCurrentUser().getCreatedTasks()
                .stream()
                .anyMatch(task -> task.getName().equals(request.getName()));
    }

}
