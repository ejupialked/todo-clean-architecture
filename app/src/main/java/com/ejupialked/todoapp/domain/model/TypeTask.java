package com.ejupialked.todoapp.domain.model;

import com.ejupialked.todoapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class TypeTask implements Serializable {

    private String name;
    private int imageID;
    private int noTasks;
    private String ID;


    public TypeTask(String name) {
        this.name = name;
        this.imageID = R.drawable.clipboard;
        this.noTasks = 0;
        ID = UUID.randomUUID().toString();

    }


    public void increseTask(){
        noTasks++;
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

    public int getNoTasks() {
        return noTasks;
    }


    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TypeTask)){
            return false;
        }
        TypeTask model = ((TypeTask) obj);
        return this.getID().equals(model.getID());
    }

    public void decreaseTask() {
        noTasks--;
    }
}

