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

    private List<TypeTask> taskTypes;
    private Map<TypeTask, ArrayList<Task>> tasks;


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




       int i = 0;
        for (TypeTask t: taskTypes) {
            tasks.put(t, new ArrayList<>());
           t.setTasks(i++);
        }


        tasks.get(taskTypes.get(0)).add(new Task("Drink 2l", "high", "false"));
        tasks.get(taskTypes.get(0)).add(new Task("Eat 4 peanuts ", "medium", "true"));
        tasks.get(taskTypes.get(0)).add(new Task("Do not eat sugar", "high", "false"));
        tasks.get(taskTypes.get(0)).add(new Task("Drink 2l", "high", "false"));
        tasks.get(taskTypes.get(0)).add(new Task("Eat 4 peanuts ", "medium", "true"));
        tasks.get(taskTypes.get(0)).add(new Task("Do not eat sugar", "high", "false"));tasks.get(taskTypes.get(0)).add(new Task("Drink 2l", "high", "false"));
        tasks.get(taskTypes.get(0)).add(new Task("Eat 4 peanuts ", "medium", "true"));
        tasks.get(taskTypes.get(0)).add(new Task("Do not eat sugar", "high", "false"));tasks.get(taskTypes.get(0)).add(new Task("Drink 2l", "high", "false"));
        tasks.get(taskTypes.get(0)).add(new Task("Eat 4 peanuts ", "medium", "true"));
        tasks.get(taskTypes.get(0)).add(new Task("Do not eat sugar", "high", "false"));

    }


    public Observable<List<Task>> tasks(TypeTask typeTask){

        ArrayList<Task> tasksL = new ArrayList<>();

        for (Map.Entry<TypeTask, ArrayList<Task>>  entry: tasks.entrySet()) {

            if(entry.getValue().size() != 0){
                tasksL = entry.getValue();
            }

        }


        return Observable.fromArray(tasksL);
    }

    @Override
    public Observable<Integer> removeTaskType(Integer position) {

        taskTypes.remove(position);

         return Observable.create(emitter -> {

            if (position != null) {
                emitter.onNext(position);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });
    }

    public Observable<List<TypeTask>> typeTaskList() {
        return Observable.fromArray(taskTypes);
    }

    @Override
    public Observable<TypeTask> createType(TypeTask t) {
        taskTypes.add(t);
        tasks.put(t, new ArrayList<>());
        return Observable.create(emitter -> {

            if (t != null) {
                emitter.onNext(t);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });
    }
}
