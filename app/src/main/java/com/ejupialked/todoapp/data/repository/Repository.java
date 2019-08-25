package com.ejupialked.todoapp.data.repository;

import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {

    Observable<List<TypeTask>> typeTaskList();
    Observable<TypeTask> createTypeTask(TypeTask typeTask);

    Observable<List<Task>> tasks(TypeTask typeTask);

    Observable<Integer> removeTaskType(Integer position);
}
