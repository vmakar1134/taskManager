package com.makar.test.service;

import com.makar.test.domain.Task;
import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;

import java.util.List;

public interface TaskService {

    Task getTask(Long id);

    Task createTask(TaskRequest taskRequest);

    Task updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);

    void shareTask(ShareTaskRequest shareTaskRequest);

    List<Task> getAll();

}
