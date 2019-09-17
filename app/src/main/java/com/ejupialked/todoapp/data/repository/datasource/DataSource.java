package com.ejupialked.todoapp.data.repository.datasource;

import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<List<TypeTask>> typeTaskList();
    Observable<TypeTask> createTypeTask(TypeTask t);
    Observable<TypeTask> editTaskType(TypeTask t);
    Observable<TypeTask> removeTaskType(TypeTask t);


    Observable<List<Task>> tasks(TypeTask t);
    Observable<Task> createTask(Task t);
    Observable<Task> removeTask(Task t);
    Observable<Task> editTask(Task t);

}
