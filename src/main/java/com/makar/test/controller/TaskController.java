package com.makar.test.controller;

import com.makar.test.domain.Task;
import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;
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
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping("")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @PostMapping("create")
    public Task createTask (@RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }

    @PostMapping("update/{id}")
    public Task updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
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
