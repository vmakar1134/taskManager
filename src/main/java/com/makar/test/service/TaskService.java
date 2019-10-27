package com.makar.test.service;

import com.makar.test.domain.request.ShareTaskRequest;
import com.makar.test.domain.request.TaskRequest;
import com.makar.test.domain.response.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getTaskDto(Long id);

    TaskDto createTask(TaskRequest taskRequest);

    TaskDto updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);

    void shareTask(ShareTaskRequest shareTaskRequest);

    List<TaskDto> getAllTaskDtos();

    List<TaskDto> getShared();

    List<TaskDto> getCreated();

}
