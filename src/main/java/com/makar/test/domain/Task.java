package com.makar.test.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task extends IdHolder {

    private String name;

    private String description;

    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserAuth createdBy;

    @ManyToMany(mappedBy = "sharedTasks", fetch = FetchType.LAZY)
    private Set<UserAuth> sharedWith = new HashSet<>();

    public Task() {
    }

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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public UserAuth getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserAuth createdBy) {
        this.createdBy = createdBy;
    }

    public Set<UserAuth> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(Set<UserAuth> sharedWith) {
        this.sharedWith = sharedWith;
    }

}
