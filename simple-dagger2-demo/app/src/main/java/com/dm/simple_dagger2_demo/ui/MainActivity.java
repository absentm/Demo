package com.dm.simple_dagger2_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dm.simple_dagger2_demo.R;
import com.dm.simple_dagger2_demo.beans.Poetry;
import com.dm.simple_dagger2_demo.di.components.DaggerMainComponent;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

// 目标类
public class MainActivity extends AppCompatActivity {

    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    @BindView(R.id.main_show_tv)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder().build().inject(this);

        initViews();
    }

    private void initViews() {
        mTextView.setText(mPoetry.getPemo());
        String json = mGson.toJson(mPoetry);
        mTextView.setText(json);

    }
}
