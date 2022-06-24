package com.hrydziushka.finalproject.controller.command.impl.go;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.service.impl.ApartmentServiceImpl;
import com.hrydziushka.finalproject.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static com.hrydziushka.finalproject.controller.PagePath.BOOK_APARTMENT_PAGE;
import static com.hrydziushka.finalproject.controller.RequestAttribute.APARTMENT;
import static com.hrydziushka.finalproject.controller.RequestAttribute.BOOKED_DATES;
import static com.hrydziushka.finalproject.controller.RequestParameter.SELECTED_APARTMENT_ID;

public class GoToBookApartmentPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Long apartmentId = Long.valueOf(request.getParameter(SELECTED_APARTMENT_ID));

        ApartmentServiceImpl apartmentService = ApartmentServiceImpl.getInstance();
        try {
            Optional<Apartment> optionalApartment = apartmentService.findApartmentById(apartmentId);
            if (optionalApartment.isPresent()) {
                request.setAttribute(APARTMENT, optionalApartment.get());
                OrderServiceImpl orderService = OrderServiceImpl.getInstance();
                LocalDate[] dates = orderService.findBookDatesByApartmentId(apartmentId);

                StringBuilder sb = new StringBuilder();
                //todo add constants
                sb.append("[");
session.setAttribute("abc",1);
                for (int i = 0; i < dates.length; i++) {
                    sb.append("\"").append(dates[i].format(DateTimeFormatter.ofPattern("d-MM-uuuu"))).append("\"");
                    if (i + 1 < dates.length) {
                        sb.append(",");
                    }
                }
                sb.append("]");
                request.setAttribute(BOOKED_DATES, sb.toString());


                return new Router(BOOK_APARTMENT_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            //todo
            throw new CommandException(e);
        }
//todo//todo
        return null;


    }
}
