package com.ejupialked.todoapp.domain.usecase;

import com.ejupialked.todoapp.data.repository.Repository;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetTasks extends UseCase<List<Task>> {


    private final Repository repository;
    private TypeTask typeTask;

    @Inject
    public GetTasks(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    public void showTasksByTypetask(TypeTask typeTask){
        this.typeTask = typeTask;
    }



    @Override
    protected Observable<List<Task>> createObservableUseCase() {
        return this.repository.tasks(typeTask);
    }
}
