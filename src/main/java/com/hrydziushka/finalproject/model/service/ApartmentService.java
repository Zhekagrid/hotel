package com.hrydziushka.finalproject.model.service;

import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    Optional<Apartment> findApartmentById(Long id) throws ServiceException;

    int apartmentsCount() throws ServiceException;

    List<Apartment> findAllApartmentForPage(int page, int apartmentsCountPerPage) throws ServiceException;
}
