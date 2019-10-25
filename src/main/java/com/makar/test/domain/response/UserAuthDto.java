package com.makar.test.domain.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAuthDto {

    private String email;

    private List<TaskDto> createdTasks = new ArrayList<>();

    private Set<TaskDto> sharedTasks = new HashSet<>();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TaskDto> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<TaskDto> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public Set<TaskDto> getSharedTasks() {
        return sharedTasks;
    }

    public void setSharedTasks(Set<TaskDto> sharedTasks) {
        this.sharedTasks = sharedTasks;
    }

}
