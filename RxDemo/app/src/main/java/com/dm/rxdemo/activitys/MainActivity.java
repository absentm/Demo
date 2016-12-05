package com.dm.rxdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dm.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lout_content)
    LinearLayout mLinearLayout;

    @BindView(R.id.btn_1)
    Button RetrefitBtn;

    @BindView(R.id.btn_2)
    Button RxJavaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_0)
    public void btn_0_click() {
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_list)
                .items(R.array.items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog,
                                            View view,
                                            int which,
                                            CharSequence text) {
                        Toast.makeText(MainActivity.this,
                                "which = " + which,
                                Toast.LENGTH_SHORT).show();
                        String textStr = (String) text;
                        switch (textStr) {
                            case "Res":
                                startActivity(new Intent(MainActivity.this, ResAty.class));
                                break;
                            case "Fragment and ViewHolder":
                                startActivity(new Intent(MainActivity.this, FragmentAty.class));
                                break;
                            case "List":
                                break;
                        }
                    }
                })
                .show();
    }

    @OnClick(R.id.btn_1)
    public void btn_1_click() {
        showSnackBar(getWindow().getDecorView(), "btn_1");
    }

    @OnClick(R.id.btn_2)
    public void btn_2_click() {
        showSnackBar(getWindow().getDecorView(), "btn_2");
    }

    private void showSnackBar(View view, String msg) {
        final Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        }).show();
    }
}
