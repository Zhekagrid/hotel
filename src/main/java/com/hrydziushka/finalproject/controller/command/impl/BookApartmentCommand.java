package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.impl.ApartmentServiceImpl;
import com.hrydziushka.finalproject.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.hrydziushka.finalproject.controller.PagePath.ERROR_404_PAGE;
import static com.hrydziushka.finalproject.controller.PageUri.HOME_PAGE_URI;
import static com.hrydziushka.finalproject.controller.RequestParameter.*;
import static com.hrydziushka.finalproject.controller.SessionAttribute.*;

public class BookApartmentCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String fromDateStr = request.getParameter(FROM_DATE);
        String toDateStr = request.getParameter(TO_DATE);
        String apartmentIdStr = request.getParameter(SELECTED_APARTMENT_ID);
        User user = (User) session.getAttribute(USER);
        Router router = new Router(ERROR_404_PAGE, Router.RouterType.FORWARD);
        if (apartmentIdStr != null && user != null) {
            Long apartmentId = Long.valueOf(apartmentIdStr);
            ApartmentServiceImpl apartmentService = ApartmentServiceImpl.getInstance();
            try {
                Optional<Apartment> optionalApartment = apartmentService.findApartmentById(apartmentId);
                if (optionalApartment.isPresent()) {
                    OrderServiceImpl orderService = OrderServiceImpl.getInstance();
                    boolean bookApartmentResult = orderService.bookApartment(fromDateStr, toDateStr, user, optionalApartment.get());
                    if (bookApartmentResult) {
                        session.setAttribute(BOOKING_SUCCESS,true);
                        router = new Router(HOME_PAGE_URI, Router.RouterType.FORWARD);
                    } else {
                        session.setAttribute(BOOKING_SUCCESS,false);
                        router = new Router((String) request.getSession().getAttribute(PREV_PAGE_URI), Router.RouterType.REDIRECT);
                    }

                }


            } catch (ServiceException e) {
                //todo log
                throw new CommandException(e);
            }


        }


        return router;
    }


}
