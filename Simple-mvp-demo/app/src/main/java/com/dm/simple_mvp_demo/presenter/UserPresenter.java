package com.dm.simple_mvp_demo.presenter;

import com.dm.simple_mvp_demo.beans.UserBean;
import com.dm.simple_mvp_demo.model.UserModel;
import com.dm.simple_mvp_demo.view.IUserView;

/**
 * UserPresenter
 * Created by dm on 17-4-18.
 */

public class UserPresenter {
    private IUserView mIUserView;
    private UserModel mUserModel;

    public UserPresenter(IUserView IUserView) {
        mIUserView = IUserView;
        mUserModel = new UserModel();
    }

    public void saverUser(int id, String userName, String userPassword) {
        mUserModel.setID(id);
        mUserModel.setUserName(userName);
        mUserModel.setUserPassword(userPassword);
    }

    public void loadUser(int id) {
        UserBean userBean = mUserModel.load(id);
        mIUserView.setUserName(userBean.getUserName());
        mIUserView.setUserPassword(userBean.getUserPassword());
    }

}
