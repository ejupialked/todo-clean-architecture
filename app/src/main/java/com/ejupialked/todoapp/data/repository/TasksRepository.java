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
        return dataSource.createType(typeTask);
    }

    @Override
    public Observable<List<Task>> tasks(TypeTask typeTask) {
        return dataSource.tasks(typeTask);
    }

    @Override
    public Observable<Integer> removeTaskType(Integer position) {
        return dataSource.removeTaskType(position);
    }


    @Override
    public Observable<TypeTask> createTask(TypeTask typeTask) {
        return dataSource.createTask(typeTask);
    }

    @Override
    public Observable<TypeTask> removeTask(TypeTask typeTask) {
        return dataSource.removeTask(typeTask);
    }

    @Override
    public Observable<TypeTask> editTaskType(TypeTask typeTask) {
        return dataSource.editTask(typeTask);
    }
}
