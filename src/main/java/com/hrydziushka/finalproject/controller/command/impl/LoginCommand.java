package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.service.UserService;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static com.hrydziushka.finalproject.controller.command.AttributeAndParameter.*;
public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
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
