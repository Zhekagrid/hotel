package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.dao.impl.UserDaoImpl;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        // TODO:   validate pass + encode

        boolean match = false;
        try {
            UserDaoImpl.getInstance().authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }
}
