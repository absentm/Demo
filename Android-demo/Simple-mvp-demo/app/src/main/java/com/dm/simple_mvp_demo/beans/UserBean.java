package com.dm.simple_mvp_demo.beans;

/**
 * UserBean
 * Created by dm on 17-4-18.
 */

public class UserBean {
    private String userName;
    private String userPassword;

    public UserBean(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
