package com.hrydziushka.finalproject.controller.command.impl.admin;

import com.hrydziushka.finalproject.controller.SessionAttribute;
import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hrydziushka.finalproject.controller.RequestParameter.*;

public class SaveUserChangesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Long userId = Long.valueOf(request.getParameter(SELECTED_USER_ID));
        String userStatus = request.getParameter(SELECTED_USER_STATUS);
        String userRole = request.getParameter(SELECTED_USER_ROLE);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            //todo if result
            boolean result = userService.updateUserStatusAndRole(userRole, userStatus, userId);

            HttpSession session = request.getSession();
            String prevPageUrl = (String) session.getAttribute(SessionAttribute.PREV_PAGE_URI);
            return new Router(prevPageUrl, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("An error occurred when executing the command to save user changes ",e);
            throw new CommandException(e);

        }


    }
}
