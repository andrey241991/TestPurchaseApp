package com.example.home_pc.testpurchaselistapp.dagger.module;

import com.example.home_pc.testpurchaselistapp.router.Router;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
    @Provides
    Router provideRouter(){
        return new Router();
    }
}
