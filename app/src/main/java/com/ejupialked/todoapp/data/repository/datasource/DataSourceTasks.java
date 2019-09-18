package com.ejupialked.todoapp.data.repository.datasource;


import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@SuppressWarnings("ALL")
@Singleton
public class DataSourceTasks implements DataSource {


    private final LinkedHashMap<String, TypeTask> typeTasks;
    private LinkedHashMap<String, ArrayList<Task>> tasks;



    @Inject
    DataSourceTasks(){
        this.typeTasks = new LinkedHashMap<>();
        tasks = new LinkedHashMap<>();

        initDataset();
    }

    private void initDataset() {



        TypeTask health = new TypeTask("Health");
        TypeTask education = new TypeTask("Education");
        TypeTask diet = new TypeTask("Diet");
        TypeTask family = new TypeTask("Family");
        TypeTask shopping = new TypeTask("Shopping");
        TypeTask meeting = new TypeTask("Meeting");
        TypeTask expenses = new TypeTask("Expenses");
        TypeTask goals = new TypeTask("Goals");


        //add ith the method
/////////////////////////////////////////////////////////////////////////////////////
        addNewTypeTask(health);
        addNewTypeTask(education);
        addNewTypeTask(diet);
        addNewTypeTask(family);
        addNewTypeTask(shopping);
        addNewTypeTask(meeting);
        addNewTypeTask(expenses);
        addNewTypeTask(goals);

        ArrayList<TypeTask> typeTasks1 = new ArrayList<>(typeTasks.values());

        typeTasks1.get(0).setImageID(R.drawable.band_aid);
        typeTasks1.get(1).setImageID(R.drawable.mortarboard);
        typeTasks1.get(2).setImageID(R.drawable.doughnut);
        typeTasks1.get(3).setImageID(R.drawable.heart);
        typeTasks1.get(4).setImageID(R.drawable.shopping_bag);
        typeTasks1.get(5).setImageID(R.drawable.chat);
        typeTasks1.get(6).setImageID(R.drawable.wallet);
        typeTasks1.get(7).setImageID(R.drawable.cup);



        Task t1 = new Task("Drink water", "high", health.getID());
        Task t2 =new Task("Train abs", "medium", health.getID());
        Task t3 = new Task("Drink water", "high", health.getID());


        Task t4 = new Task("Drink water", "high", health.getID());
        Task t5 =new Task("Train abs", "medium", health.getID());
        Task t6 = new Task("Drink water", "high", health.getID());

        Task t7 = new Task("Drink water", "high", health.getID());
        Task t8 =new Task("Train abs", "medium", health.getID());
        Task t9 = new Task("Drink water", "high", health.getID());

        addNewTask(t1);
        addNewTask(t2);
        addNewTask(t3);
        addNewTask(t4);
        addNewTask(t5);
        addNewTask(t6);
        addNewTask(t7);
        addNewTask(t8);
        addNewTask(t9);

        for (int i = 0; i < 20; i++) {
            addNewTask(new Task("Drink water", "high", health.getID()));
        }


        Task t20 =new Task("Call father", "high", family.getID());
        Task t21 =new Task("birthday sister", "high", family.getID());

        addNewTask(t4);
        addNewTask(t5);
    }


    @Override
    public Observable<Task> createTask(Task task)  {

        addNewTask(task);
        return Observable.create(emitter -> {
            if (task != null) {
                emitter.onNext(task);
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable("Error task type"));
            }
        });
    }

    @Override
    public Observable<Task> removeTask(Task t) {

        removeNewTask(t);
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

    @Override
    public Observable<Task> editTask(Task t) {
        Task t1;
        try {
            t1 = searchTaskByID(t);
            t1 = t;
        }catch (Exception e){
            e.printStackTrace();
        }

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

    public Observable<List<Task>> tasks(TypeTask typeTask){
        ArrayList<Task> o = tasks.get(typeTask.getID());
        return Observable.fromArray(o);
    }



    public Observable<List<TypeTask>> typeTaskList() {
        ArrayList<TypeTask> t = new ArrayList<TypeTask>(typeTasks.values());
        return Observable.fromArray(t);
    }




    @Override
    public Observable<TypeTask> createTypeTask(TypeTask t) {

        typeTasks.put(t.getID(), t);
        tasks.put(t.getID(), new ArrayList<>()); // for tasks
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


    @Override
    public Observable<TypeTask> editTaskType(TypeTask typeTask) {

        typeTasks.put(typeTask.getID(), typeTask);

        return  Observable.create(emitter -> {
            if (typeTask != null) {
                emitter.onNext(typeTask);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });
    }

    @Override
    public Observable<TypeTask> removeTaskType(TypeTask t) {
        typeTasks.remove(t.getID());
        return  Observable.create(emitter -> {
            if (t != null) {
                emitter.onNext(t);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });
    }

    public Task searchTaskByID(Task t) throws Exception {


        for(Task x: tasks.get(t.getParentID())){
            if(x.equals(t)){
                return x;
            }
        }

        throw new Exception("task not found");
    }


    public void addNewTypeTask(TypeTask t){
        typeTasks.put(t.getID(), t);
        tasks.put(t.getID(), new ArrayList<>());
    }

    public void addNewTask(Task t){
        TypeTask tt = typeTasks.get(t.getParentID());

        tt.increseTask();
        if(tasks.get(tt.getID()) == null){
            tasks.put(tt.getID(), new ArrayList<>());
        }else{
            tasks.get(tt.getID()).add(t);
        }
    }

    public void removeNewTask(Task t){
        TypeTask tt = typeTasks.get(t.getParentID());


        tasks.get(tt.getID()).remove(t);
        tt.decreaseTask();


    }



    public static ArrayList<Integer> getImages(){
        ArrayList<Integer> images = new ArrayList<>();

        images.add(R.drawable.briefcase);
        images.add(R.drawable.doughnut);
        images.add(R.drawable.band_aid);
        images.add(R.drawable.mortarboard);
        images.add(R.drawable.shopping_bag);

        return images;

    }


}
