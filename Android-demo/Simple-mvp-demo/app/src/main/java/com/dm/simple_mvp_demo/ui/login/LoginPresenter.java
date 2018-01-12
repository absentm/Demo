package com.dm.simple_mvp_demo.ui.login;

/**
 * LoginPresenter
 * Created by dm on 17-4-19.
 */

public class LoginPresenter {
    private ILoginModel mILoginModel;
    private ILoginView mILoginView;

    public LoginPresenter(ILoginView ILoginView) {
        this.mILoginView = ILoginView;
        mILoginModel = new LoginModel();
    }

    public void registerUser(String userName, String userPassword) {
        boolean isRegister = mILoginModel.onRegister(userName, userPassword);

        if (isRegister) {
            mILoginView.onRegisterSuccessed();
        } else {
            mILoginView.onRegisterFailed();
        }
    }

    public void logininUser(String userName, String userPassword) {
        boolean isLoginin = mILoginModel.onLoginin(userName, userPassword);

        if (isLoginin) {
            mILoginView.onLoginSuccessed();
        } else {
            mILoginView.onLoginFailed();
        }
    }
}
