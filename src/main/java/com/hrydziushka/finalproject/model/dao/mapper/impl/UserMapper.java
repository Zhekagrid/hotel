package com.hrydziushka.finalproject.model.dao.mapper.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.mapper.Mapper;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class UserMapper implements Mapper<User> {
    private static UserMapper instance = new UserMapper();


    private UserMapper() {
    }

    public static UserMapper getInstance() {
        return instance;
    }

    @Override
    public User mapRow(ResultSet resultSet) throws DaoException {
        User user;
        try {
            user = User.newBuilder()
                    .setId(Long.valueOf(resultSet.getString(USER_ID)))
                    .setLogin(resultSet.getString(LOGIN))
                    .setEmail(resultSet.getString(EMAIL))
                    .setPhoneNumber(resultSet.getString(PHONE_NUMBER))
                    .setBalance(resultSet.getBigDecimal(BALANCE))
                    .setUserRole(UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()))
                    .setUserStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                    .build();

        } catch (SQLException e) {
            //todo  log
            throw new DaoException(e);
        }
        return user;
    }
}
