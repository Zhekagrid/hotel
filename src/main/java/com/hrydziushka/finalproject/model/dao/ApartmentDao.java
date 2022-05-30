package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.Apartment;

import java.util.Optional;

public interface ApartmentDao {
    int apartmentsCount() throws DaoException;

    Optional<Apartment> findApartmentById(Long apartmentId) throws DaoException;

}
