package com.dm.simple_dagger2_demo.di.modules;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * MainModule 使用 @Module 注解表示这个类提供生成一些实例用于注入
 * Created by dm on 17-4-20.
 */

@Module
public class MainModule {

    /**
     * 使用 @Provides 注解表示这个方法使用来创建某个实例对象的；
     * 本例中创建返回 Gson 对象。
     * <p>
     * 方法名随意，一般采用 provideXXX 结构。
     *
     * @return
     */
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
