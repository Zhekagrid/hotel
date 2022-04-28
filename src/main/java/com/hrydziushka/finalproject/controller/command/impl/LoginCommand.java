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

import java.util.Optional;

import static com.hrydziushka.finalproject.controller.RequestParameter.LOGIN;
import static com.hrydziushka.finalproject.controller.RequestParameter.PASSWORD;
import static com.hrydziushka.finalproject.controller.SessionAttribute.*;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;

        HttpSession session = request.getSession();

        try {
            Optional<User> optionalUser = userService.signIn(login, password);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();
                session.setAttribute(USER, user);
                session.setAttribute(ROLE, user.getUserRole());
                session.setAttribute(STATUS, user.getUserStatus());
                //todo add pages
                page = "pages/main.jsp";
            } else {
                request.setAttribute("login_msg", "Incorrect login or pass");
                page = "index.jsp";
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(page, Router.RouterType.REDIRECT);
    }
}
