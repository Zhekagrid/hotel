package com.hrydziushka.finalproject.model.dao.mapper.impl;

import com.hrydziushka.finalproject.model.dao.mapper.Mapper;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.entity.UserStatus;
import com.hrydziushka.finalproject.model.entity.User;

import java.sql.ResultSet;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class UserMapper implements Mapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) {
//User user=User.newBuilder()
//        .setLogin(resultSet.getString(LOGIN))
//        .setEmail(resultSet.getString(EMAIL))
//        .setPhoneNumber(resultSet.getString(PHONE_NUMBER))
//        .setBalance(resultSet.getBigDecimal(BALANCE))
//        .setUserRole(UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()))
//        .setUserStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
//        .build();
        return null;
    }
}
