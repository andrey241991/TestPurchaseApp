package com.example.home_pc.testpurchaselistapp.app;

import android.app.Application;

import com.example.home_pc.testpurchaselistapp.dagger.component.AppComponent;
import com.example.home_pc.testpurchaselistapp.dagger.component.DaggerAppComponent;

public class App extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    public static AppComponent getDaggerAppComponent() {
        return appComponent;
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().build();
    }
}
