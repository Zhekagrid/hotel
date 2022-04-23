package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.dao.impl.UserDaoImpl;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.UserService;
import com.hrydziushka.finalproject.util.validator.impl.UserValidatorImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {

        Optional<User> optionalUser = Optional.empty();
        try {
            UserValidatorImpl userValidator = UserValidatorImpl.getInstance();
            if (userValidator.validateLogin(login) && userValidator.validatePassword(password)) {
                UserDaoImpl userDao = UserDaoImpl.getInstance();
                optionalUser = userDao.signIn(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }
}
