package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login,String password) throws DaoException;
}
