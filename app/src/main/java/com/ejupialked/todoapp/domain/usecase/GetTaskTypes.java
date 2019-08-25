package com.ejupialked.todoapp.domain.usecase;

import com.ejupialked.todoapp.data.repository.Repository;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetTaskTypes extends UseCase<List<TypeTask>> {

    private final Repository repository;

    @Inject
    public GetTaskTypes(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<TypeTask>> createObservableUseCase() {
        return this.repository.typeTaskList();
    }
}
