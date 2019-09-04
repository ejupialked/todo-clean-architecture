package com.ejupialked.todoapp.domain.model;

import com.ejupialked.todoapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class TypeTask implements Serializable {

    private String name;
    private ArrayList<Task> tasks;
    private int imageID;
    String uniqueID;


    public TypeTask(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.imageID = R.drawable.clipboard;
        uniqueID = UUID.randomUUID().toString();

    }

    public void setName(String name) {
        this.name = name;
    }


    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public void removeTask(int index){
        this.tasks.remove(index);
    }

    public String getName() {
        return name;
    }

    public void addNewTask(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String getUniqueID() {
        return uniqueID;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TypeTask)){
            return false;
        }
        TypeTask model = ((TypeTask) obj);
        return this.getUniqueID().equals(model.getUniqueID());
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }
}

