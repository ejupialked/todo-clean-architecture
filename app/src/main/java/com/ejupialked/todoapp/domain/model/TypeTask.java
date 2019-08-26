package com.ejupialked.todoapp.domain.model;

import java.io.Serializable;

public class TypeTask implements Serializable {

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
