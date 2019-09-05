package com.ejupialked.todoapp.di.modules;

import android.content.Context;

import com.ejupialked.todoapp.TodoApp;
import com.ejupialked.todoapp.data.repository.Repository;
import com.ejupialked.todoapp.data.repository.TasksRepository;
import com.ejupialked.todoapp.data.repository.datasource.DataSource;
import com.ejupialked.todoapp.data.repository.datasource.DataSourceTasks;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class MainModule {

    private final TodoApp todoApp;

    public MainModule(TodoApp todoApp) {
        this.todoApp = todoApp;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return todoApp;
    }

    @Provides
    @Singleton
    Repository provideRepository(TasksRepository tasksRepository) {
        return tasksRepository;
    }

    @Provides
    @Singleton
    DataSource provideDataSource(DataSourceTasks dataSourceTasks) {
        return dataSourceTasks;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}

