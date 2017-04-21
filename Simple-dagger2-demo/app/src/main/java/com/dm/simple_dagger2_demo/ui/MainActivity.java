package com.dm.simple_dagger2_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.dm.simple_dagger2_demo.R;
import com.dm.simple_dagger2_demo.beans.Poetry;
import com.dm.simple_dagger2_demo.di.components.MainComponent;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 目标类
public class MainActivity extends AppCompatActivity {

    // 目标类中使用 Inject 注入实例对象
    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    @BindView(R.id.main_show_tv)
    TextView mTextView;

    @BindView(R.id.main_open_btn)
    Button mOpenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // DaggerMainComponent 是 Dagger2 自动生成的类,
        // 使用 Dagger2 生成组件进行构造，并注入
        MainComponent.getInstance().inject(this);

        initViews();
    }

    private void initViews() {
        mTextView.setText(mPoetry.getPemo());
        String json = mGson.toJson(mPoetry);
        mTextView.setText(json);

    }

    @OnClick(R.id.main_open_btn)
    public void open_btn_click() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
