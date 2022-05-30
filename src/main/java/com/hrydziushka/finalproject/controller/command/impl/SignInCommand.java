package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.UserService;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.hrydziushka.finalproject.Message.INCORRECT_LOGIN_INFORMATION_MESSAGE;
import static com.hrydziushka.finalproject.controller.PagePath.SIGN_IN_PAGE;
import static com.hrydziushka.finalproject.controller.PageUri.HOME_PAGE_URI;
import static com.hrydziushka.finalproject.controller.RequestParameter.LOGIN;
import static com.hrydziushka.finalproject.controller.RequestParameter.PASSWORD;
import static com.hrydziushka.finalproject.controller.SessionAttribute.*;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        //todo delete
        if (login.equals("Admin1")) {
            password = "Admin_1";
        }


        UserService userService = UserServiceImpl.getInstance();
        Router router;

        HttpSession session = request.getSession();

        try {
            Optional<User> optionalUser = userService.signIn(login, password);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();
                session.setAttribute(USER, user);
                session.setAttribute(ROLE, user.getUserRole());
                session.setAttribute(STATUS, user.getUserStatus());

                router = new Router(HOME_PAGE_URI, Router.RouterType.FORWARD);

            } else {

                session.setAttribute(INCORRECT_LOGIN_INFORMATION, INCORRECT_LOGIN_INFORMATION_MESSAGE);
                router = new Router(SIGN_IN_PAGE, Router.RouterType.FORWARD);

            }

        } catch (ServiceException e) {
            logger.error("An error occurred when executing the user authentication command", e);
            throw new CommandException(e);
        }

        return router;
    }
}
