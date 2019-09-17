package com.ejupialked.todoapp.data.repository;

import androidx.annotation.NonNull;

import com.ejupialked.todoapp.data.repository.datasource.DataSource;
import com.ejupialked.todoapp.data.repository.datasource.DataSourceTasks;
import com.ejupialked.todoapp.domain.model.Task;
import com.ejupialked.todoapp.domain.model.TypeTask;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TasksRepository implements Repository {
    private final DataSource dataSource;

    @Inject
    TasksRepository(@NonNull DataSourceTasks dataSourceTasks) {
        this.dataSource = dataSourceTasks;
    }

    @Override
    public Observable<List<TypeTask>> typeTaskList() {
        return dataSource.typeTaskList();
    }

    @Override
    public Observable<TypeTask> createTypeTask(TypeTask typeTask) {
        return dataSource.createTypeTask(typeTask);
    }

    @Override
    public Observable<List<Task>> tasks(TypeTask typeTask) {
        return dataSource.tasks(typeTask);
    }

    @Override
    public Observable<TypeTask> removeTaskType(TypeTask t) {
        return dataSource.removeTaskType(t);
    }


    @Override
    public Observable<Task> createTask(Task t) {
        return dataSource.createTask(t);
    }

    @Override
    public Observable<Task> removeTask(Task t) {
        return dataSource.removeTask(t);
    }

    @Override
    public Observable<Task> editTask(Task t) {
        return dataSource.editTask(t);
    }

    @Override
    public Observable<TypeTask> editTaskType(TypeTask t) {
        return dataSource.editTaskType(t);
    }
}
