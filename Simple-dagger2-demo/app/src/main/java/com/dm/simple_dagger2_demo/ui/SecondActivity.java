package com.dm.simple_dagger2_demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dm.simple_dagger2_demo.R;
import com.dm.simple_dagger2_demo.beans.Poetry;
import com.dm.simple_dagger2_demo.di.components.MainComponent;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SecondActivity
 * Created by dm on 17-4-21.
 */

public class SecondActivity extends AppCompatActivity {

    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    @BindView(R.id.second_show_tv)
    TextView mSecondShowTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ButterKnife.bind(this);

        MainComponent.getInstance().inject(this);

        initViews();

    }

    private void initViews() {
        setTitle("SecondActivity");

        // 设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSecondShowTv.setText(mGson.toJson(mPoetry));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                SecondActivity.this.finish();
                break;
        }

        return true;
    }
}
