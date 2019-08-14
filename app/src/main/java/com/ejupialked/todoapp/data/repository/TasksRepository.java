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
    public Observable<List<Task>> tasks(TypeTask typeTask) {
        return dataSource.tasks(typeTask);
    }
}
