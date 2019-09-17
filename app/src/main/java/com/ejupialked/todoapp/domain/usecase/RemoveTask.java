package com.ejupialked.todoapp.domain.usecase;

import com.ejupialked.todoapp.data.repository.Repository;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class RemoveTask extends UseCase<Task> {

    private final Repository repository;
    private Task t;

    @Inject
    public RemoveTask(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }


    public void removeTask(Task t){
        this.t = t;
    }

    @Override
    protected Observable<Task> createObservableUseCase() {
        return this.repository.removeTask(t);
    }
}
