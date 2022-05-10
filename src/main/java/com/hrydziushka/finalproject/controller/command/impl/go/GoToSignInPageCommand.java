package com.hrydziushka.finalproject.controller.command.impl.go;

import com.hrydziushka.finalproject.controller.PagePath;
import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static com.hrydziushka.finalproject.controller.PagePath.SIGN_IN_PAGE;

public class GoToSignInPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(SIGN_IN_PAGE, Router.RouterType.REDIRECT);
    }
}
