package com.ejupialked.todoapp.data.repository.datasource;

import com.ejupialked.todoapp.R;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@SuppressWarnings("ALL")
@Singleton
public class DataSourceTasks implements DataSource{


    private ArrayList<TypeTask> typeTasks;


    @Inject
    DataSourceTasks(){

        initDataset();
    }

    private void initDataset() {

        typeTasks = new ArrayList<>();



        typeTasks.add(new TypeTask("Health"));
        typeTasks.add(new TypeTask("Education"));
        typeTasks.add(new TypeTask("Diet"));
        typeTasks.add(new TypeTask("Family"));
        typeTasks.add(new TypeTask("Shopping"));
        typeTasks.add(new TypeTask("Work"));


        typeTasks.get(0).setImageID(R.drawable.band_aid);
        typeTasks.get(1).setImageID(R.drawable.mortarboard);
        typeTasks.get(2).setImageID(R.drawable.doughnut);
        typeTasks.get(3).setImageID(R.drawable.heart);
        typeTasks.get(4).setImageID(R.drawable.shopping_bag);
        typeTasks.get(5).setImageID(R.drawable.briefcase);


        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));
        typeTasks.get(0).addNewTask(new Task("Drink water", "high", "no" ));
        typeTasks.get(0).addNewTask(new Task("Train abs", "medium", "yes"));



        typeTasks.get(3).addNewTask(new Task("Call father", "high", "no"));
        typeTasks.get(3).addNewTask(new Task("birthday sister", "high", "no"));

    }


    @Override
    public Observable<TypeTask> createTask(TypeTask typeTask)  {
        TypeTask found = null;

        try {
            found = searchTaskType(typeTask, typeTasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        typeTasks.remove(found);
        typeTasks.add(typeTask);

        return Observable.create(emitter -> {

            if (typeTask != null) {
                emitter.onNext(typeTask);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });
    }

    public Observable<List<Task>> tasks(TypeTask typeTask){

        ArrayList<Task> found = null;
        try {
           found = searchTasks(typeTask, typeTasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Observable.fromArray(found);
    }

    @Override
    public Observable<Integer> removeTaskType(Integer position) {

         typeTasks.remove(position);

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
        return Observable.fromArray(new ArrayList<>(typeTasks));
    }

    @Override
    public Observable<TypeTask> createType(TypeTask t) {
        typeTasks.add(t);
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



    public ArrayList<Task> searchTasks(TypeTask t, ArrayList<TypeTask> taskTypes) throws Exception {

        for(TypeTask x: taskTypes){
            if(x.equals(t)){
                return t.getTasks();
            }
        }

        throw new Exception("Tasks not found");
    }


    @Override
    public Observable<TypeTask> removeTask(TypeTask typeTask) {


        TypeTask found = null;

        try {
            found = searchTaskType(typeTask, typeTasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        typeTasks.remove(found);
        typeTasks.add(typeTask);


        return Observable.create(emitter -> {
            if (typeTask != null) {
                emitter.onNext(typeTask);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("Error task type"));
            }
        });


    }

    public TypeTask searchTaskType(TypeTask t, ArrayList<TypeTask> taskTypes) throws Exception {

        for(TypeTask x: taskTypes){
            if(x.equals(t)){
                return t;
            }
        }

        throw new Exception("TaskType not found");
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
