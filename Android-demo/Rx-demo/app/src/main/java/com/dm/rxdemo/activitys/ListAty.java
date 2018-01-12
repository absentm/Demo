package com.dm.rxdemo.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.dm.rxdemo.R;
import com.xgc1986.ripplebutton.widget.RippleButton;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ListAty
 * Created by dm on 16-12-8.
 */
public class ListAty extends AppCompatActivity {
    @BindView(R.id.list_first_name)
    EditText mListFirstName;
    @BindView(R.id.list_mid_name)
    EditText mListMidName;
    @BindView(R.id.list_last_name)
    EditText mListLastName;

    @BindViews({R.id.list_first_name, R.id.list_mid_name, R.id.list_last_name})
    List<EditText> nameViews;

    @BindView(R.id.list_btn)
    RippleButton mListBtn;

    private int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_list);
        ButterKnife.bind(this);

        initBar();
    }

    private void initBar() {
        setTitle("LIST");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };

    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    @OnClick(R.id.list_btn)
    public void onClick() {
        switch (flag) {
            case 0:
                ButterKnife.apply(nameViews, DISABLE);
                flag = 1;
                mListBtn.setButtonColor(Color.RED);
                break;
            case 1:
                ButterKnife.apply(nameViews, ENABLED, true);
                mListBtn.setButtonColor(Color.DKGRAY);
                flag = 0;
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
}
