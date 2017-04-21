package com.dm.simple_dagger2_demo.beans;

import javax.inject.Inject;

/**
 * Poetry: 源类
 * Created by dm on 17-4-20.
 */

public class Poetry {
    private String mPemo;

    // 使用 Inject 标记构造函数，表示用它来注入到目标对象中去
    @Inject
    public Poetry() {
        mPemo = "Life is like sea !";
    }

    public Poetry(String pemo) {
        mPemo = pemo;
    }

    public String getPemo() {
        return mPemo;
    }

}
