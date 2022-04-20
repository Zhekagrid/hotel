package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.model.entity.AbstractEntity;
import com.hrydziushka.finalproject.exception.DaoException;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    boolean insert(T t) throws DaoException;

    boolean delete(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    T update(T t) throws DaoException;
}
