package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.ApartmentImage;

import java.util.Map;
import java.util.Set;

public interface ImageDao {
    Set<ApartmentImage> findAllApartmentImagesByApartmentId(Long apartmentId) throws DaoException;

    Map<Long, Set<ApartmentImage>> findAllImagesOfAllApartments() throws DaoException;
}
