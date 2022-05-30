package com.hrydziushka.finalproject.controller.command.impl.go;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.service.impl.ApartmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.hrydziushka.finalproject.controller.PagePath.APARTMENTS_PAGE;

import static com.hrydziushka.finalproject.controller.RequestAttribute.*;
import static com.hrydziushka.finalproject.controller.RequestParameter.*;

public class GoToApartmentsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_APARTMENTS_COUNT_PER_PAGE = 2;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        int page = request.getParameter(PAGE) != null ? Integer.parseInt(request.getParameter(PAGE)) : DEFAULT_PAGE;
        int apartmentsCountPerPage = request.getParameter(APARTMENTS_COUNT_PER_PAGE) != null ? Integer.parseInt(request.getParameter(APARTMENTS_COUNT_PER_PAGE)) : DEFAULT_APARTMENTS_COUNT_PER_PAGE;
        try {
            ApartmentServiceImpl apartmentService = ApartmentServiceImpl.getInstance();
            List<Apartment> apartments = apartmentService.findAllApartmentForPage(page, apartmentsCountPerPage);
            int apartmentsCount = apartmentService.apartmentsCount();
            int pagesCount = (int) Math.ceil(apartmentsCount * 1.0 / apartmentsCountPerPage);
            request.setAttribute(APARTMENTS_COUNT_PER_PAGE, apartmentsCountPerPage);
            request.setAttribute(PAGES_COUNT, pagesCount);
            request.setAttribute(APARTMENTS, apartments);
            request.setAttribute(PAGE, page);
            return new Router(APARTMENTS_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("There was an error when executing the command to show the apartments",e);
            throw new CommandException(e);

        }


    }
}
