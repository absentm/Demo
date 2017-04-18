package com.dm.simple_mvp_demo.model;

import com.dm.simple_mvp_demo.beans.UserBean;

/**
 * IUserModel
 * Created by dm on 17-4-18.
 */

public interface IUserModel {
    void setID(int id);

    void setUserName(String userName);

    void setUserPassword(String userPassword);

    UserBean load(int id);
}
