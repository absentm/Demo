package com.dm.simple_dagger2_demo.di.components;

import com.dm.simple_dagger2_demo.di.modules.MainModule;
import com.dm.simple_dagger2_demo.ui.MainActivity;

import dagger.Component;

/**
 * MainComponent
 * Created by dm on 17-4-20.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
