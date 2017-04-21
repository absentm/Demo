package com.dm.simple_dagger2_demo.di.components;

import com.dm.simple_dagger2_demo.di.modules.MainModule;
import com.dm.simple_dagger2_demo.di.modules.PoetryModule;
import com.dm.simple_dagger2_demo.ui.MainActivity;
import com.dm.simple_dagger2_demo.ui.SecondActivity;

import dagger.Component;

/**
 * MainComponent：源类实例与目标类的连接器，
 * 在 @Component 注解中只能是 interface 或者抽象类。
 * <p>
 * modules = MainModule.class: 表示 Component 会从 MainModule 类中取那些
 * 使用 @Provides 注解的方法来生成需要注入的实例。
 * <p>
 * Created by dm on 17-4-20.
 */

@Component(modules = {MainModule.class, PoetryModule.class})
public abstract class MainComponent {

    /**
     * 定义连接器名称，需要用到这个连接器的对象；
     * 就是这个对象里面需要注入的属性（ @Inject 标记）；
     * 方法名可以随意更改，建议使用 Inject 即可。
     *
     * @param mainActivity
     */
    public abstract void inject(MainActivity mainActivity);

    public abstract void inject(SecondActivity secondActivity);

    private static MainComponent sMainComponent;

    public static MainComponent getInstance() {
        if (sMainComponent == null) {
            sMainComponent = DaggerMainComponent.builder().build();
        }

        return sMainComponent;
    }

}
