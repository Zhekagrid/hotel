package com.hrydziushka.finalproject.controller.command.impl.go;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.hrydziushka.finalproject.controller.PagePath.HOME_PAGE;
import static com.hrydziushka.finalproject.controller.RequestAttribute.*;
import static com.hrydziushka.finalproject.controller.RequestAttribute.USERS;
import static com.hrydziushka.finalproject.controller.RequestParameter.PAGE;
import static com.hrydziushka.finalproject.controller.RequestParameter.USERS_COUNT_PER_PAGE;
import static com.hrydziushka.finalproject.controller.SessionAttribute.USER;

public class GoToHomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_USERS_COUNT_PER_PAGE = 2;
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute(USER);
        if (user!=null&&user.getUserRole() == UserRole.ADMIN) {
            int page = request.getParameter(PAGE) != null ? Integer.parseInt(request.getParameter(PAGE)) : DEFAULT_PAGE;
            int usersCountPerPage = request.getParameter(USERS_COUNT_PER_PAGE) != null ? Integer.parseInt(request.getParameter(USERS_COUNT_PER_PAGE)) : DEFAULT_USERS_COUNT_PER_PAGE;
            try {
                UserServiceImpl userService=UserServiceImpl.getInstance();
                List<User> users = userService.findAllUsersForPage(page, usersCountPerPage);
                int usersCount = userService.countOfUsers();
                int pagesCount = (int) Math.ceil(usersCount * 1.0 / usersCountPerPage);
                request.setAttribute(USERS_COUNT_PER_PAGE, usersCountPerPage);
                request.setAttribute(PAGES_COUNT, pagesCount);
                request.setAttribute(USERS, users);
                request.setAttribute(PAGE, page);
                //todo

                //session.setAttribute(CURRENT_PAGE,);
                return new Router(HOME_PAGE, Router.RouterType.FORWARD);
            } catch (ServiceException e) {
                logger.error("An error occurred when executing the command to go to the home page",e);
                throw new CommandException(e);

            }

        }
        //todo
        return new Router(HOME_PAGE, Router.RouterType.FORWARD);
    }
}
