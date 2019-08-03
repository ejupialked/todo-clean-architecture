package com.ejupialked.todoapp.di.components;

import android.content.Context;

import com.ejupialked.todoapp.di.modules.MainModule;
import com.ejupialked.todoapp.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);

    Context context();
}
