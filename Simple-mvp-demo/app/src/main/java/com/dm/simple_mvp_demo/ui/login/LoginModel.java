package com.dm.simple_mvp_demo.ui.login;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginModel
 * Created by dm on 17-4-19.
 */

public class LoginModel implements ILoginModel {
    private Map<String, String> userMap = new HashMap<>();

    @Override
    public boolean onRegister(String userName, String userPassword) {
        if (!userMap.containsKey(userName)) {
            userMap.put(userName, userPassword);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean onLoginin(String userName, String userPassword) {
        return userPassword.equals(userMap.get(userName));
    }
}
