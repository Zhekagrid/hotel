package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.hrydziushka.finalproject.controller.PagePath.HOME_PAGE;
import static com.hrydziushka.finalproject.controller.SessionAttribute.*;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
       request.getSession().invalidate();
        return new Router(HOME_PAGE, Router.RouterType.REDIRECT);
    }
}
