package com.dm.simple_mvp_demo.model;

import android.util.SparseArray;

import com.dm.simple_mvp_demo.beans.UserBean;

/**
 * UserModel
 * Created by dm on 17-4-18.
 */

public class UserModel implements IUserModel {

    private String mUserName;
    private String mUserPassword;
    private int mID;

    private SparseArray<UserBean> mUserBeanSparseArray = new SparseArray<>();

    @Override
    public void setID(int id) {
        mID = id;
    }

    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
        UserBean userBean = new UserBean(mUserName, mUserPassword);
        mUserBeanSparseArray.append(mID, userBean);
    }

    @Override
    public UserBean load(int id) {
        mID = id;
        UserBean userBean = mUserBeanSparseArray.get(mID,
                new UserBean("Not Found!", "Not Found!"));

        return userBean;
    }
}
