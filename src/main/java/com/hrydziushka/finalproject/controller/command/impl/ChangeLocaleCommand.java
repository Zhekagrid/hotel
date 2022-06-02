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
    private static final String ENGLISH_LOCALE = "en";
    private static final String GERMAN_LOCALE = "de";
    private static final String RUSSIAN_LOCALE = "ru";

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();

        switch (locale) {
            case RUSSIAN_LOCALE -> session.setAttribute(SessionAttribute.LOCALE, RUSSIAN_LOCALE);
            case GERMAN_LOCALE -> session.setAttribute(SessionAttribute.LOCALE, GERMAN_LOCALE);
            default -> session.setAttribute(SessionAttribute.LOCALE, ENGLISH_LOCALE);
        }


//todo refactor
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        logger.info("Locale changed, and new locale is " + locale);

        return new Router(currentPage, Router.RouterType.FORWARD);


    }
}
