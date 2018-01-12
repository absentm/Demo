package com.dm.simple_dagger2_demo.di.modules;

import com.dm.simple_dagger2_demo.beans.Poetry;

import dagger.Module;
import dagger.Provides;

/**
 * PoetryModule
 * Created by dm on 17-4-21.
 */

@Module
public class PoetryModule {

    @Provides
    public Poetry providePoetry(String poems) {
        return new Poetry(poems);
    }

    @Provides
    public String providePoems() {
        return "Make it, and get it!";
    }
}
