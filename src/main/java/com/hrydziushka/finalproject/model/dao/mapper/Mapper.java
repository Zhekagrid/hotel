package com.hrydziushka.finalproject.model.dao.mapper;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.AbstractEntity;

import java.sql.ResultSet;

public interface Mapper<T extends AbstractEntity>{
    T mapResultSet(ResultSet resultSet) throws DaoException;
}
