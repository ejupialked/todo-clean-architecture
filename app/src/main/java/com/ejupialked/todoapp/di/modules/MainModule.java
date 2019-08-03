package com.ejupialked.todoapp.di.modules;

import android.content.Context;

import com.ejupialked.todoapp.TODOApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class MainModule {

    private final TODOApplication todoApplication;

    public MainModule(TODOApplication todoApplication) {
        this.todoApplication = todoApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return todoApplication;
    }

    /*@Provides
    @Singleton
    Repository provideRepository(TeamsRepository teamsRepository) {
        return teamsRepository;
    }*/

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

