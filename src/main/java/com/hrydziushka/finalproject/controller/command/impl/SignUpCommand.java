package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.hrydziushka.finalproject.controller.PageUri.HOME_PAGE_URI;
import static com.hrydziushka.finalproject.controller.PageUri.SIGN_UP_PAGE_URI;
import static com.hrydziushka.finalproject.controller.SessionAttribute.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;

        HttpSession session = request.getSession();
         Map<String, String[]> userInformation = new HashMap<>(request.getParameterMap());

        try {

            UserServiceImpl userService = UserServiceImpl.getInstance();
            Optional<User> optionalUser = userService.signUp(userInformation);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(USER, user);
                session.setAttribute(ROLE, user.getUserRole());
                session.setAttribute(STATUS, user.getUserStatus());
                router = new Router(HOME_PAGE_URI, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(SIGN_UP_FORM_DATA, userInformation);
                router = new Router(SIGN_UP_PAGE_URI, Router.RouterType.FORWARD);
            }

            return router;

        } catch (ServiceException e) {
            logger.error("An error occurred when executing the user registration command",e);
            throw new CommandException(e);
        }


    }
}
