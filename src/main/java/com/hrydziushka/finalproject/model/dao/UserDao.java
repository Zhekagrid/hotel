package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.User;

import java.util.Optional;

public interface UserDao {
   Optional<User> authenticate(String login, String password) throws DaoException;

}
