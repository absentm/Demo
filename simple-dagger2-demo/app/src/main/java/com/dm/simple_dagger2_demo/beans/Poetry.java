package com.dm.simple_dagger2_demo.beans;

import javax.inject.Inject;

/**
 * Poetry: 源类
 * Created by dm on 17-4-20.
 */

public class Poetry {
    private String mPemo;

    @Inject
    public Poetry() {
        mPemo = "Life is like sea !";
    }

    public String getPemo() {
        return mPemo;
    }

}
