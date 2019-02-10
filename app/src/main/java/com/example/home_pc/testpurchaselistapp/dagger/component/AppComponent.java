package com.example.home_pc.testpurchaselistapp.dagger.component;

import com.example.home_pc.testpurchaselistapp.dagger.module.UtilsModule;
import com.example.home_pc.testpurchaselistapp.ui.main.view.MainActivity;

import dagger.Component;

@Component(modules = {UtilsModule.class})
public interface AppComponent {
       void inject(MainActivity mainActivity);
}
