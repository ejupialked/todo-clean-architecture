package com.ejupialked.todoapp.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class RemoveTaskType extends UseCase<Integer> {


    RemoveTaskType(Scheduler executorThread, Scheduler uiThread) {
        super(executorThread, uiThread);
    }

    @Override
    protected Observable<Integer> createObservableUseCase() {
        return null;
    }
}
