package com.makar.test.domain.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {

    private String name;

    private String description;

    private Long createdBy;

    private List<Long> sharedWith = new ArrayList<>();

    private LocalDateTime deadline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public List<Long> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<Long> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

}
