package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao {
    Optional<User> signIn(String login, String password) throws DaoException;

    int usersCount() throws DaoException;

    void updateUserPassword(String hashPassword, Long userId) throws DaoException;


    Optional<User> findUserById(Long userId) throws DaoException;

    boolean userWithSpecificEmailExists(String email) throws DaoException;

    boolean userWithSpecificLoginExists(String login) throws DaoException;

    boolean updateUserBalanceById(BigDecimal newBalance, Long userId) throws DaoException;
}
