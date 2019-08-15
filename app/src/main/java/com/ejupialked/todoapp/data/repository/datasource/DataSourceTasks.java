package com.ejupialked.todoapp.data.repository.datasource;

import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataSourceTasks implements DataSource{

    List<TypeTask> taskTypes;
    Map<TypeTask, List<Task>> tasks;


    @Inject
    public DataSourceTasks(){

        taskTypes = new ArrayList<>();
        tasks = new HashMap<>();

        taskTypes.add(new TypeTask("Health", 4));
        taskTypes.add(new TypeTask("Education", 243));
        taskTypes.add(new TypeTask("Diet", 3));
        taskTypes.add(new TypeTask("Family", 0));
        taskTypes.add(new TypeTask("Shopping", 2));
        taskTypes.add(new TypeTask("Work", 1));


        for (TypeTask t: taskTypes) {
            tasks.put(t, new ArrayList<Task>());
            t.setTasks(tasks.get(t).size());
        }

    }


    public Observable<List<Task>> tasks(TypeTask typeTask){
        return Observable.fromArray(tasks.get(typeTask));
    }

    public Observable<List<TypeTask>>  typeTaskList() {
        return Observable.fromArray(taskTypes);
    }
}
