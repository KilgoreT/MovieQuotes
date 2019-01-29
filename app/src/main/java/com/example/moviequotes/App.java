package com.example.moviequotes;

import android.app.Application;

import com.example.moviequotes.di.component.ApplicationComponent;
import com.example.moviequotes.di.component.DaggerApplicationComponent;
import com.example.moviequotes.di.module.ApplicationModule;
import com.example.moviequotes.di.module.PresenterModule;

public class App extends Application {

    private static ApplicationComponent applicationComponent;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .presenterModule(new PresenterModule())
                .build();

    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
