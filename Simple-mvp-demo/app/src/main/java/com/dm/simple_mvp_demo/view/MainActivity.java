package com.dm.simple_mvp_demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dm.simple_mvp_demo.R;
import com.dm.simple_mvp_demo.presenter.UserPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IUserView {
    private EditText mIdEt;
    private EditText mUserNameEt;
    private EditText mUserPasswdET;

    private Button mSaveBtn;
    private Button mReasBtn;

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mIdEt = (EditText) findViewById(R.id.main_id_et);
        mUserNameEt = (EditText) findViewById(R.id.main_name_et);
        mUserPasswdET = (EditText) findViewById(R.id.main_passwd_et);
        mSaveBtn = (Button) findViewById(R.id.main_save_btn);
        mReasBtn = (Button) findViewById(R.id.main_read_btn);

        mUserPresenter = new UserPresenter(this);
        mSaveBtn.setOnClickListener(this);
        mReasBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_save_btn:
                mUserPresenter.saverUser(getID(), getUserName(), getUserPassword());
                break;
            case R.id.main_read_btn:
                mUserPresenter.loadUser(getID());
                break;
            default:
                break;
        }

    }

    @Override
    public int getID() {
        return Integer.parseInt(mIdEt.getText().toString().trim());
    }

    @Override
    public String getUserName() {
        return mUserNameEt.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return mUserPasswdET.getText().toString().trim();
    }

    @Override
    public void setUserName(String userName) {
        mUserNameEt.setText(userName);
    }

    @Override
    public void setUserPassword(String userPassword) {
        mUserNameEt.setText(userPassword);
    }
}
