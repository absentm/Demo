package com.dm.simple_dagger2_demo.di.modules;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * MainModule
 * Created by dm on 17-4-20.
 */

@Module
public class MainModule {

    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
