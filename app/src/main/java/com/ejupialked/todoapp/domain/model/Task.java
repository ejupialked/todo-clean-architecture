package com.ejupialked.todoapp.domain.model;

public class Task {

  private String descrption;
  private String priority;
  private String isCompleted;

    public Task(String descrption, String priority, String isCompleted) {
        this.descrption = descrption;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
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
