package com.hrydziushka.finalproject.model.dao.mapper.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.mapper.Mapper;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class UserMapperImpl implements Mapper<User> {
    private static final Logger logger = LogManager.getLogger();
    private static UserMapperImpl instance = new UserMapperImpl();


    private UserMapperImpl() {
    }

    public static UserMapperImpl getInstance() {
        return instance;
    }

    @Override
    public User mapResultSet(ResultSet resultSet) throws DaoException {

        try {
            User user = User.newBuilder().setId(Long.valueOf(resultSet.getString(USER_ID)))
                    .setLogin(resultSet.getString(LOGIN))
                    .setEmail(resultSet.getString(EMAIL))
                    .setPhoneNumber(resultSet.getString(PHONE_NUMBER))
                    .setBalance(resultSet.getBigDecimal(BALANCE))
                    .setUserRole(UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()))
                    .setUserStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                    .build();
            return user;
        } catch (SQLException e) {
            logger.error("An error occurred when creating a User object in the mapper", e);
            throw new DaoException("An error occurred when creating a User object in the mapper",e);
        }

    }
}
