package com.absentm.spbt.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Listen servlet request
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {
    private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        logger.info("request start init...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("session id: {}", httpServletRequest.getRequestedSessionId());
        logger.info("request url: {}", httpServletRequest.getRequestURL());

        httpServletRequest.setAttribute("requestName", "hhhhhhhhh");


    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("request end ...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        logger.info("request name: {}", httpServletRequest.getAttribute("requestName"));
    }
}
