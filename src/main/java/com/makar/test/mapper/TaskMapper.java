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
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setCreatedBy(task.getCreatedBy().getId());
        dto.setSharedWith(task.getSharedWith()
                .stream()
                .map(IdHolder::getId)
                .collect(Collectors.toList()));
        dto.setDeadline(task.getDeadline());

        return dto;
    }

}
