package com.makar.test.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task extends IdHolder {

    private String name;

    private String description;

    private LocalDateTime startDateTime;

    @ManyToOne
    private UserAuth createdBy;

    @ManyToMany(mappedBy = "sharedTasks")
    private Set<UserAuth> sharedWith = new HashSet<>();

    public Task() {
    }

    public Task(String description, String name) {
        super();
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Set<UserAuth> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(Set<UserAuth> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public UserAuth getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserAuth createdBy) {
        this.createdBy = createdBy;
    }

}
