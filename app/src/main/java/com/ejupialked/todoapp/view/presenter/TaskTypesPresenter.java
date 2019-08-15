package com.ejupialked.todoapp.view.presenter;

import androidx.annotation.NonNull;

import com.ejupialked.todoapp.domain.model.TypeTask;
import com.ejupialked.todoapp.domain.usecase.GetTaskTypes;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TaskTypesPresenter extends Presenter<TaskTypesPresenter.View> {

    private GetTaskTypes getTaskTypes;

    @Inject
    public TaskTypesPresenter(@NonNull GetTaskTypes getTaskTypes) {
        this.getTaskTypes = getTaskTypes;
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

    public void destroy(){
        this.getTaskTypes.dispose();
        setView(null);
    }


    public interface View extends Presenter.View {
        void showTaskTypes(List<TypeTask> typeTaskList);
        void showNameTaskType(String name);
    }
}
