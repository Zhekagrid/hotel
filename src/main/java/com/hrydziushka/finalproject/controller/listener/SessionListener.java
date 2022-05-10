package com.hrydziushka.finalproject.controller.listener;


import com.hrydziushka.finalproject.controller.PagePath;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static com.hrydziushka.finalproject.controller.SessionAttribute.*;
import static com.hrydziushka.finalproject.model.entity.UserRole.GUEST;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        HttpSession session = se.getSession();
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(CURRENT_PAGE, PagePath.HOME_PAGE);
        session.setAttribute(ROLE,GUEST);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);

    }
}
