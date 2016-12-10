package com.dm.rxdemo.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dm.rxdemo.R;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * RxJavaBaseAty
 * Created by dm on 16-12-10.
 */
public class RxJavaBaseAty extends BaseBarWithBackActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_rxjava_base);
        setTitle("RX_BASE");

        // 创建被观察者，正宗写法
        Observable switcher = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("On");
                subscriber.onNext("OFF");
                subscriber.onNext("On");
                subscriber.onNext("On");
                subscriber.onCompleted();
            }
        });

        // 创建被观察者，懒汉写法1
        Observable switcher1 = Observable.just("On", "Off", "On", "On");

        // 创建被观察者，懒汉写法2
        String[] kk = {"On", "Off", "On", "On"};
        Observable switcher2 = Observable.from(kk);

        // 创建观察者，正常写法
        Subscriber light = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Logger.d("Finished !!!");

            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());

            }

            @Override
            public void onNext(String s) {
                Logger.d("This is " + s);

            }
        };

        // 创建观察者，非正常写法
        Action1 light1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d("In Action this is " + s);
            }

        };

        // 创建订阅关系
        switcher.subscribe(light);

    }
}
