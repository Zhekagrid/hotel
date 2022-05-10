package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hrydziushka.finalproject.controller.PagePath.SIGN_UP_PAGE;

public class SignUpCommand implements Command {
private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session=request.getSession();
        session.setAttribute("login",request.getParameter("login"));
        session.setAttribute("email",request.getParameter("email"));
        session.setAttribute("password",request.getParameter("password"));
        session.setAttribute("repeat_password",request.getParameter("repeat_password"));
        logger.info("sign up"+ request.getParameter("login")+request.getParameter("email"));
        return new Router(SIGN_UP_PAGE, Router.RouterType.REDIRECT);
    }
}
