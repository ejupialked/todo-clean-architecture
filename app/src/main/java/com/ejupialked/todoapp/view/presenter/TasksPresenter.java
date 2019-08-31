package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTask;
import com.ejupialked.todoapp.domain.usecase.GetTasks;
import com.ejupialked.todoapp.view.activity.customcomponents.CustomDialogTask;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TasksPresenter extends Presenter<TasksPresenter.View>{

    private GetTasks getTasks;
    private AddTask addTask;
    private TypeTask typeTask;

    @Inject
    public TasksPresenter(@NonNull GetTasks getTasks, @NonNull AddTask addTask) {
        this.getTasks = getTasks;
        this.addTask = addTask;
    }




    @Override
    public void initialize() {
        super.initialize();

        getTasks.showTasksByTypetask(typeTask);
        getTasks.execute(new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(List<Task> tasks) {
                getView().showTasks(tasks);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                // TODO: 31/08/2019
            }
        });


    }





    public void destroy() {
        this.getTasks.dispose();
        setView(null);
    }


    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public void onTaskCreated(Task task) {

        typeTask.addNewTask(task);

        addTask.createTask(typeTask);

        addTask.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().updateTasks(typeTask.getTasks());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public interface View extends Presenter.View, CustomDialogTask.CustomDialogListener  {
        void showTasks(List<Task> tasks);
        void updateTasks(List<Task> tasks);
    }
}
