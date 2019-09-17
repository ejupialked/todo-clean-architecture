package com.ejupialked.todoapp.domain.usecase;

import com.ejupialked.todoapp.data.repository.Repository;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class AddTask extends UseCase<Task> {

    private final Repository repository;
    private Task t;
    @Inject
    public AddTask(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }


    public void createTask(Task task){
        this.t = task;
    }

    @Override
    protected Observable<Task> createObservableUseCase() {
        return this.repository.createTask(t);
    }
}
