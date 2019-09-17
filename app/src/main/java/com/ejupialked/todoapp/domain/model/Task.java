package com.ejupialked.todoapp.domain.model;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {

    private String ID;
    private String parentID;
    private String description;
    private String priority;
    private boolean isCompleted;


  private String date;

    public Task(String description, String priority, String parentID) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
        this.parentID = parentID;
        this.ID = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return "22/02/19 3:00";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public String getParentID() {
        return parentID;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
