package com.dm.simple_mvp_demo.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dm.simple_mvp_demo.R;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * LoginActivity
 * Created by dm on 17-4-19.
 */

public class LoginActivity extends AppCompatActivity
        implements ILoginView, View.OnClickListener {

    private MaterialEditText mUserNameMEt;
    private MaterialEditText mUserPasswordMEt;

    private Button mRegisterBtn;
    private Button mLogininBtn;

    private Toast mToast;
    private String inputUserNameStr;
    private String inputPasswordStr;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mUserNameMEt = (MaterialEditText) findViewById(R.id.login_username_met);
        mUserPasswordMEt = (MaterialEditText) findViewById(R.id.login_userpassword_met);
        mRegisterBtn = (Button) findViewById(R.id.login_register_btn);
        mLogininBtn = (Button) findViewById(R.id.login_loginin_btn);

        mRegisterBtn.setOnClickListener(this);
        mLogininBtn.setOnClickListener(this);

        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_register_btn:
                if (checkInputInfo()) {
                    mLoginPresenter.registerUser(inputUserNameStr, inputPasswordStr);
                }
                break;
            case R.id.login_loginin_btn:
                if (checkInputInfo()) {
                    mLoginPresenter.logininUser(inputUserNameStr, inputPasswordStr);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public boolean checkInputInfo() {
        inputUserNameStr = mUserNameMEt.getText().toString().trim();
        inputPasswordStr = mUserPasswordMEt.getText().toString().trim();

        if (inputUserNameStr.equals("")) {
            mUserNameMEt.setError("User name can not be empty!");
            return false;
        }

        if (inputPasswordStr.equals("")) {
            mUserPasswordMEt.setError("User password can not be empty!");
            return false;
        }

        return true;
    }

    @Override
    public void onRegisterSuccessed() {
        showToast("Register successed!");
    }

    @Override
    public void onRegisterFailed() {
        showToast("user is exist!");
    }

    @Override
    public void onLoginSuccessed() {
        showToast("loginin successed!");
    }

    @Override
    public void onLoginFailed() {
        showToast("user name or password is error!");
    }

    private void showToast(String toastString) {
        if (mToast == null) {
            mToast = Toast.makeText(LoginActivity.this,
                    toastString, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(toastString);
        }

        mToast.show();
    }
}
