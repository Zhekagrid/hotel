package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.model.dao.impl.UserDaoImpl;
import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        // TODO:   validate pass +md5

        boolean match = false;
        try {
            match = UserDaoImpl.getInstance().authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
