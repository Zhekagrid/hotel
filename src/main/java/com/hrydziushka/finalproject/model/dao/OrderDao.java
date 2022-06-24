package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
 List<ImmutablePair<LocalDate,LocalDate>> findBooksDateByApartmentId(Long apartmentId) throws DaoException;

}
