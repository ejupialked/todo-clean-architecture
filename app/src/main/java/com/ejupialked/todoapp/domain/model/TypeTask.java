package com.ejupialked.todoapp.domain.model;

public class TypeTask {

    String name;
    int tasks;

    public TypeTask(String name, int tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(int tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public int getTasks() {
        return tasks;
    }
}
