package com.hrydziushka.finalproject.controller.listener;


import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static com.hrydziushka.finalproject.controller.SessionAttribute.LOCALE;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        ConnectionPool.getInstance();
        HttpSession session = se.getSession();
        session.setAttribute(LOCALE, DEFAULT_LOCALE);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        ConnectionPool.getInstance().destroyPool();
    }
}
