package com.ejupialked.todoapp;

import android.app.Application;

import com.ejupialked.todoapp.di.components.DaggerMainComponent;
import com.ejupialked.todoapp.di.components.MainComponent;
import com.ejupialked.todoapp.di.modules.MainModule;

public class TODOApplication extends Application {

    private MainComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
      mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
