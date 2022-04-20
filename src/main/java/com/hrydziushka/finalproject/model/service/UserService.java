package com.hrydziushka.finalproject.model.service;

import com.hrydziushka.finalproject.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login,String password) throws ServiceException;
}
