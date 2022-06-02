package com.hrydziushka.finalproject.controller.command.impl.go;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.service.impl.ApartmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.hrydziushka.finalproject.controller.PagePath.BOOK_APARTMENT_PAGE;
import static com.hrydziushka.finalproject.controller.RequestAttribute.APARTMENT;
import static com.hrydziushka.finalproject.controller.RequestParameter.SELECTED_APARTMENT_ID;
import static com.hrydziushka.finalproject.controller.SessionAttribute.USER;

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
