package com.hrydziushka.finalproject.controller.listener;


import com.hrydziushka.finalproject.controller.PagePath;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Optional;

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
        session.setAttribute(ROLE, GUEST);
        ///todo delete
//        UserServiceImpl userService = UserServiceImpl.getInstance();
//        Optional<User> optionalUser = null;
//        try {
//            optionalUser = userService.signIn("Admin1", "Admin_1");
//            if (optionalUser.isPresent()) {
//
//                User user = optionalUser.get();
//                session.setAttribute(USER, user);
//                session.setAttribute(ROLE, user.getUserRole());
//                session.setAttribute(STATUS, user.getUserStatus());
//
//
//            }
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


    }


}
