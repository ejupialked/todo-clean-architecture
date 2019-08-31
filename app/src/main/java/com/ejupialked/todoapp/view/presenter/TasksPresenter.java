package com.ejupialked.todoapp.view.presenter;


import androidx.annotation.NonNull;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.GetTasks;
import com.ejupialked.todoapp.view.activity.customcomponents.CustomDialog;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TasksPresenter extends Presenter<TasksPresenter.View>{

    private GetTasks getTasks;
    private TypeTask typeTask;

    @Inject
    public TasksPresenter(@NonNull GetTasks getTasks) {
        this.getTasks = getTasks;
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

    public interface View extends Presenter.View, CustomDialog.CustomDialogListener  {
        void showTasks(List<Task> tasks);
    }
}
