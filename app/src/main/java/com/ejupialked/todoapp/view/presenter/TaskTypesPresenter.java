package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;

import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.AddTaskType;
import com.ejupialked.todoapp.domain.usecase.GetTaskTypes;
import com.ejupialked.todoapp.view.activity.CustomDialog;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TaskTypesPresenter extends Presenter<TaskTypesPresenter.View> {

    private GetTaskTypes getTaskTypes;
    private AddTaskType addTaskType;

    @Inject
    public TaskTypesPresenter(@NonNull GetTaskTypes getTaskTypes, @NonNull AddTaskType addTaskType) {
        this.getTaskTypes = getTaskTypes;
        this.addTaskType = addTaskType;
    }


    @Override
    public void initialize() {
        super.initialize();

        getView().showLoading();

        getTaskTypes.execute(new DisposableObserver<List<TypeTask>>() {
            @Override
            public void onNext(List<TypeTask> typeTasks) {
                getView().showTaskTypes(typeTasks);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void onTaskTypeClicked(String name){
        getView().showNameTaskType(name);
    }

    public void onTaskTypeCreated(TypeTask t){

        addTaskType.createTaskType(t);

        addTaskType.execute(new DisposableObserver<TypeTask>() {
            @Override
            public void onNext(TypeTask typeTask) {
                getView().updateTypeTasks(typeTask);
            }

            @Override
            public void onError(Throwable e) {
                // show error
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                // show ui
            }
        });
    }


    public void destroy(){
        this.getTaskTypes.dispose();
        setView(null);
    }


    public interface View extends Presenter.View, CustomDialog.CustomDialogListener {
        void showTaskTypes(List<TypeTask> typeTaskList);
        void updateTypeTasks(TypeTask t);
        void showNameTaskType(String name);
    }
}
