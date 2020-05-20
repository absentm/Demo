package com.absentm.spbt.listener;

import com.absentm.spbt.entity.User;
import com.absentm.spbt.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.ArrayList;

/**
 * Listen servlet context object
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 1. get application context
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();

        // 2. get user info
        UserService userService = applicationContext.getBean(UserService.class);
        ArrayList<User> allUserList = userService.getAllUserList();

        // 3. get application domain object, and then save to here
        ServletContext servletContext = applicationContext.getBean(ServletContext.class);
        servletContext.setAttribute("allUserList", allUserList);
    }
}
