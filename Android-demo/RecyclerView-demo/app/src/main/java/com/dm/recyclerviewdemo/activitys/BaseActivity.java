package com.dm.recyclerviewdemo.activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;

/**
 * BaseActivity
 * Created by dm on 16-11-18.
 */

public class BaseActivity extends AppCompatActivity {
    private static final int statusBarColor = Color.parseColor("#3F51B5");

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(BaseActivity.this, statusBarColor);
    }
}
