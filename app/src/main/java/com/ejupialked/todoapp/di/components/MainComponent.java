package com.ejupialked.todoapp.di.components;

import android.content.Context;

import com.ejupialked.todoapp.di.modules.MainModule;
import com.ejupialked.todoapp.view.activity.TaskTypeActivity;
import com.ejupialked.todoapp.view.activity.TasksActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(TaskTypeActivity activity);

    void inject(TasksActivity activity);

    Context context();
}
