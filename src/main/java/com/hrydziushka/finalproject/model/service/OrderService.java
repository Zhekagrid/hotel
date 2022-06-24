package com.hrydziushka.finalproject.model.service;

import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.User;

import java.time.LocalDate;

public interface OrderService {
    LocalDate[] findBookDatesByApartmentId(Long apartmentId) throws ServiceException;

    boolean bookApartment(String fromDate, String toDate, User user, Apartment apartment) throws ServiceException;
}
