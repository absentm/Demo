package com.dm.simple_mvp_demo.ui.login;

/**
 * ILoginView
 * Created by dm on 17-4-19.
 */

public interface ILoginView {
    boolean checkInputInfo();

    void onRegisterSuccessed();

    void onRegisterFailed();

    void onLoginSuccessed();

    void onLoginFailed();
}
