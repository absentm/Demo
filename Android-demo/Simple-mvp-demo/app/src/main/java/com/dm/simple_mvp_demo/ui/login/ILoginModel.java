package com.dm.simple_mvp_demo.ui.login;

/**
 * ILoginModel
 * Created by dm on 17-4-19.
 */

public interface ILoginModel {
    boolean onRegister(String userName, String userPassword);

    boolean onLoginin(String userName, String userPassword);
}
