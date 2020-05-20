package com.absentm.spbt.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listen http session object
 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);
    public Integer userCounter = 0;

    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("User is online...");
        userCounter++;
        httpSessionEvent.getSession().getServletContext().setAttribute("userCounter", userCounter);

    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("User is offline...");
        userCounter--;
        httpSessionEvent.getSession().getServletContext().setAttribute("userCounter", userCounter);
    }
}
