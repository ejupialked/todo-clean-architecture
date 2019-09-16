package com.ejupialked.todoapp.domain.model;

import java.io.Serializable;
import java.util.Calendar;

public class Task implements Serializable {

  private String description;
  private String priority;
  private boolean isCompleted;
  private String date;

    public Task(String description, String priority,boolean isCompleted) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;
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
