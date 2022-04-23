package com.hrydziushka.finalproject.model.service;

import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;

import java.util.Optional;

public interface UserService {
  Optional<User> signIn(String login, String password) throws ServiceException;
}
