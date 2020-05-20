package com.absentm.spbt.event;

import com.absentm.spbt.entity.User;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;

public class MyEvent extends ApplicationEvent {
    private ArrayList<User> userArrayList;

    public MyEvent(Object source, ArrayList<User> userArrayList) {
        super(source);
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
