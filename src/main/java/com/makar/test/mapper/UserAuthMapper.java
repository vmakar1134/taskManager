package com.makar.test.mapper;

import com.makar.test.domain.UserAuth;
import com.makar.test.domain.response.UserAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserAuthMapper implements EntityMapper<UserAuth, UserAuthDto> {

    private final TaskMapper taskMapper;

    @Autowired
    public UserAuthMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public UserAuthDto toDto(UserAuth userAuth) {
        UserAuthDto dto = new UserAuthDto();
        dto.setEmail(userAuth.getEmail());
        dto.setCreatedTasks(taskMapper.toDtoList(userAuth.getCreatedTasks()));
        dto.setSharedTasks(new HashSet<>(taskMapper.toDtoList(userAuth.getCreatedTasks())));
        return dto;
    }

}
