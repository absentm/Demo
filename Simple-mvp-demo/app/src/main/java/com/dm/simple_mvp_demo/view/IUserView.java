package com.dm.simple_mvp_demo.view;

/**
 * IUserView
 * Created by dm on 17-4-18.
 */

public interface IUserView {
    int getID();

    String getUserName();

    String getUserPassword();

    void setUserName(String userName);

    void setUserPassword(String userPassword);
}
