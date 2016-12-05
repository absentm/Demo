package com.dm.rxdemo.activitys;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dm.rxdemo.R;
import com.xgc1986.ripplebutton.widget.RippleButton;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ResAty
 * Created by dm on 16-12-3.
 */
public class ResAty extends AppCompatActivity {

    @BindView(R.id.tv_res)
    TextView mTvRes;

    @BindView(R.id.btn_res)
    RippleButton mBtnRes;

    @BindString(R.string.changed_text)
    String changedStr;

    @BindColor(android.R.color.holo_orange_light)
    int holo_orange_light;

    @BindDrawable(R.drawable.bg)
    Drawable mDrawable;

    @BindDimen(R.dimen.text_size_xlarge)
    float mFloatTextSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_res);
        ButterKnife.bind(this);

        initBarView();

    }

    private void initBarView() {
        setTitle("RES");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @OnClick(R.id.btn_res)
    public void onClick() {
        mTvRes.setText(changedStr);
        mTvRes.setTextColor(holo_orange_light);
        mTvRes.setTextSize(mFloatTextSize);

        // can change the var
//        mDrawable = new IconicsDrawable(ResAty.this)
//                .icon(FontAwesome.Icon.faw_android)
//                .color(Color.RED)
//                .sizeDp(48);
        mTvRes.setBackground(mDrawable);
    }
}
