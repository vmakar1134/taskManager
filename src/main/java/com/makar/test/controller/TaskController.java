package com.makar.test.controller;

import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;
import com.makar.test.domain.response.TaskDto;
import com.makar.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return taskService.getTaskDto(id);
    }

    @GetMapping("all")
    public List<TaskDto> getAll() {
        return taskService.getAllTaskDtos();
    }

    @GetMapping("shared")
    public List<TaskDto> getShared() {
        return taskService.getShared();
    }

    @GetMapping("created")
    public List<TaskDto> getCreated() {
        return taskService.getCreated();
    }

    @PostMapping("create")
    public TaskDto createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PostMapping("update/{id}")
    public TaskDto updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
        return taskService.updateTask(id, taskRequest);
    }

    @PostMapping("share")
    public void shareTask(@RequestBody ShareTaskRequest shareTaskRequest) {
        taskService.shareTask(shareTaskRequest);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
