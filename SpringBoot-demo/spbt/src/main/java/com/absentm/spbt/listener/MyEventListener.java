package com.absentm.spbt.listener;

import com.absentm.spbt.entity.User;
import com.absentm.spbt.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MyEventListener.class);

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        ArrayList<User> userArrayList = myEvent.getUserArrayList();
        logger.info("Listener in myEvent: {}", userArrayList.toString());
    }
}
