package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.SessionAttribute;
import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hrydziushka.finalproject.controller.RequestParameter.LANGUAGE;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(LANGUAGE);
        //todo switch
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        //todo refactor
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        logger.info("Locale changed, and new locale is "+locale);

        return new Router(currentPage, Router.RouterType.FORWARD);


    }
}
