package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.AbstractEntity;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    long insert(T t) throws DaoException;

    boolean delete(T t) throws DaoException;

    List<T> findAllInRange(int offset, int rowCount) throws DaoException;

    boolean update(T t) throws DaoException;
}
