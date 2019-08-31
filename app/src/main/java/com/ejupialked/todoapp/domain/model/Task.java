package com.ejupialked.todoapp.domain.model;

import java.io.Serializable;

public class Task implements Serializable {

  private String description;
  private String priority;
  private String isCompleted;

    public Task(String description, String priority, String isCompleted) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }
}
