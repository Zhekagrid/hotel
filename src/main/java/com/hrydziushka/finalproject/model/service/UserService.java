package com.hrydziushka.finalproject.model.service;

import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String login, String password) throws ServiceException;

    Optional<User> signUp(Map<String, String[]> userInformation) throws ServiceException;

    boolean emailExists(String email) throws ServiceException;

    boolean loginExists(String login) throws ServiceException;

    int countOfUsers() throws ServiceException;

    Optional<User> findUserById(Long id) throws ServiceException;

    boolean updateUserStatusAndRole(String role, String status, Long userId) throws ServiceException;

    List<User> findAllUsersForPage(int page, int usersCountPerPage) throws ServiceException;
}
