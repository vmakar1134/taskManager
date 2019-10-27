package com.makar.test.mapper;

import com.makar.test.domain.IdHolder;
import com.makar.test.domain.Task;
import com.makar.test.domain.response.TaskDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskMapper implements EntityMapper<Task, TaskDto> {

    @Override
    public TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setCreatedBy(task.getCreatedBy().getId());
        dto.setDeadline(task.getDeadline());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setSharedWith(task.getSharedWith()
                .stream()
                .map(IdHolder::getId)
                .collect(Collectors.toList()));

        return dto;
    }

}
