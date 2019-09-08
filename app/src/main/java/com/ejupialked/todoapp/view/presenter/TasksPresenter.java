package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTask;
import com.ejupialked.todoapp.domain.usecase.GetTasks;
import com.ejupialked.todoapp.domain.usecase.RemoveTask;
import com.ejupialked.todoapp.view.customview.CustomDialogTask;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TasksPresenter extends Presenter<TasksPresenter.View>{

    private GetTasks getTasks;
    private AddTask addTask;
    private RemoveTask removeTask;

    private TypeTask typeTask;

    @Inject
    public TasksPresenter(@NonNull GetTasks getTasks,
                          @NonNull AddTask addTask,
                          @NonNull RemoveTask removeTask) {

        this.getTasks = getTasks;
        this.addTask = addTask;
        this.removeTask = removeTask;
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
        bindView(null);
    }

    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public void onTaskCreated(Task task) {

        addTask.createTask(typeTask, task);

        addTask.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().addTask(task);
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

        removeTask.removeTaskAtPostion(typeTask, position);

        removeTask.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
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
    public void onTaskEdited(int position) {

    }



    public interface View extends Presenter.View, CustomDialogTask.CustomDialogListener  {
        void showTasks(List<Task> tasks);
        void removeTask(int i);
        void editTask(Task t);
        void openDialogCreateNewTask();
        void openDialogEditTask(Task t);
        void addTask(Task t);
    }
}
