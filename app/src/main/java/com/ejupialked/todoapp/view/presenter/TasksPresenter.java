package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTask;
import com.ejupialked.todoapp.domain.usecase.EditTask;
import com.ejupialked.todoapp.domain.usecase.GetTasks;
import com.ejupialked.todoapp.domain.usecase.RemoveTask;

import com.ejupialked.todoapp.view.activity.TasksActivity;
import com.ejupialked.todoapp.view.customview.CustomDialogTask;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TasksPresenter extends Presenter<TasksPresenter.View>{

    private GetTasks getTasks;
    private AddTask addTask;
    private RemoveTask removeTask;
    private EditTask editTask;

    private TypeTask typeTask;

    @Inject
    public TasksPresenter(@NonNull GetTasks getTasks,
                          @NonNull AddTask addTask,
                          @NonNull RemoveTask removeTask,
                          @NonNull EditTask editTask) {

        this.getTasks = getTasks;
        this.addTask = addTask;
        this.removeTask = removeTask;
        this.editTask = editTask;
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
        bind(null);
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public void onTaskCreated(String description, String priority, String date) {

        Task t = new Task(description, priority, typeTask.getID());
        t.setDate(date);

        addTask.createTask(t);

        addTask.execute(new DisposableObserver<Task>() {
            @Override
            public void onNext(Task t) {
                getView().addTask(t);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void onTaskRemoved(int position) {
        Task task = ((TasksActivity) getView())
                .getRecyclerViewAdapter()
                .getTaskTypeAtPosition(position);

        removeTask.removeTask(task);

        removeTask.execute(new DisposableObserver<Task>() {
            @Override
            public void onNext(Task t) {
                getView().removeTask(position);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });

    }



    /**
     * todo
     * @param position the position of the task in the list
     */
    public void onTaskEdited(Task t) {
        editTask.editTask(t);
        editTask.execute(new DisposableObserver<Task>() {
            @Override
            public void onNext(Task task) {
                getView().showEditTask(task);
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
        void removeTask(int i);
        void showEditTask(Task task);
        void openDialogEditTask(int position);
        void openDialogCreateNewTask();
        void addTask(Task t);
    }
}
