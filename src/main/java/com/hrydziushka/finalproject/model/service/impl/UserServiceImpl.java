package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.dao.impl.UserDaoImpl;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.entity.UserRole;
import com.hrydziushka.finalproject.model.entity.UserStatus;
import com.hrydziushka.finalproject.model.service.UserService;
import com.hrydziushka.finalproject.util.PasswordEncryptor;
import com.hrydziushka.finalproject.util.validator.UserValidator;
import com.hrydziushka.finalproject.util.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hrydziushka.finalproject.Message.NOT_UNIQUE_EMAIL;
import static com.hrydziushka.finalproject.Message.NOT_UNIQUE_LOGIN;
import static com.hrydziushka.finalproject.controller.RequestParameter.*;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
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
            UserValidator userValidator = UserValidatorImpl.getInstance();
            if (userValidator.validateLogin(login) && userValidator.validatePassword(password)) {
                UserDaoImpl userDao = UserDaoImpl.getInstance();
                optionalUser = userDao.signIn(login, password);
            }
        } catch (DaoException e) {
            logger.error("An error occurred during user authentication ", e);
            throw new ServiceException("An error occurred during user authentication ", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> signUp(Map<String, String[]> userInformation) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        String login = userInformation.get(LOGIN)[0];
        String password = userInformation.get(PASSWORD)[0];
        String email = userInformation.get(EMAIL)[0];
        UserValidatorImpl validator = UserValidatorImpl.getInstance();
        boolean isFormValid = validator.validateSignUpForm(userInformation);
        if (isFormValid) {
            boolean isUserDataUnique = true;
            if (emailExists(email)) {
                String[] emailData = new String[2];
                emailData[0] = email;
                emailData[1] = NOT_UNIQUE_EMAIL;
                userInformation.put(EMAIL, emailData);
                isUserDataUnique = false;
            }
            if (loginExists(login)) {
                String[] loginData = new String[2];
                loginData[0] = login;
                loginData[1] = NOT_UNIQUE_LOGIN;
                userInformation.put(LOGIN, loginData);
                isUserDataUnique = false;
            }
            if (isUserDataUnique) {
                User user = User.newBuilder()
                        .setLogin(login)
                        .setEmail(email)
                        .setUserRole(UserRole.USER)
                        .setUserStatus(UserStatus.ACTIVE)
                        .setBalance(BigDecimal.valueOf(0))
                        .build();
                UserDaoImpl userDao = UserDaoImpl.getInstance();
                try {
                    Long userId = userDao.insert(user);
                    user.setId(userId);
                    String hashPassword = PasswordEncryptor.getInstance().hashPassword(password);
                    userDao.updateUserPassword(hashPassword, user.getId());
                    optionalUser = Optional.of(user);
                } catch (DaoException e) {
                    logger.error("There was an error when registering a new user", e);
                    throw new ServiceException("There was an error when registering a new user", e);

                }
            }
        }
        return optionalUser;

    }

    @Override
    public boolean emailExists(String email) throws ServiceException {

        try {
            return UserDaoImpl.getInstance().userWithSpecificEmailExists(email);

        } catch (DaoException e) {
            logger.error("An error occurred while checking the database for the uniqueness of email: " + email, e);
            throw new ServiceException("An error occurred while checking the database for the uniqueness of email: " + email, e);

        }

    }

    @Override
    public boolean loginExists(String login) throws ServiceException {
        try {
            return UserDaoImpl.getInstance().userWithSpecificLoginExists(login);

        } catch (DaoException e) {
            logger.error("An error occurred while checking the database for the uniqueness of login: " + login, e);
            throw new ServiceException("An error occurred while checking the database for the uniqueness of login: " + login, e);

        }
    }

    @Override
    public int countOfUsers() throws ServiceException {

        try {
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            return userDao.usersCount();
        } catch (DaoException e) {
            logger.error("An error occurred while counting the number of users in the database", e);
            throw new ServiceException("An error occurred while counting the number of users in the database", e);

        }
    }

    @Override
    public Optional<User> findUserById(Long id) throws ServiceException {
        try {
            UserDaoImpl userDao = UserDaoImpl.getInstance();

            return userDao.findUserById(id);
        } catch (DaoException e) {
            logger.error("An error occurred when searching for a user with id:" + id, e);
            throw new ServiceException("An error occurred when searching for a user with id:" + id, e);

        }
    }

    @Override
    public boolean updateUserStatusAndRole(String role, String status, Long userId) throws ServiceException {
        if (role != null && status != null && userId != null) {

            UserStatus userStatus;
            UserRole userRole;
            try {
                userStatus = UserStatus.valueOf(status);
                userRole = UserRole.valueOf(role);
            } catch (IllegalArgumentException e) {
                logger.error("An error occurred when trying to change a user's role or status. Incorrect role or status. Role: " + role + "Status: " + status, e);
                throw new ServiceException("An error occurred when trying to change a user's role or status. Incorrect role or status. Role: " + role + "Status: " + status, e);

            }
            try {

                UserDaoImpl userDao = UserDaoImpl.getInstance();

                Optional<User> optionalUser = userDao.findUserById(userId);

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setUserRole(userRole);
                    user.setUserStatus(userStatus);
                    return userDao.update(user);

                }
            } catch (DaoException e) {

                logger.error("An error occurred when trying to change a user's role or status.  Role: " + role + "Status: " + status, e);
                throw new ServiceException("An error occurred when trying to change a user's role or status.  Role: " + role + "Status: " + status, e);

            }
        }


        return false;
    }

    @Override
    public List<User> findAllUsersForPage(int page, int usersCountPerPage) throws ServiceException {
        try {
            int usersOffset = (page - 1) * usersCountPerPage;
            return UserDaoImpl.getInstance().findAllInRange(usersOffset, usersCountPerPage);

        } catch (DaoException e) {
            logger.error("There was an error when finding for users for " + page + " page and with the number of users per page " + usersCountPerPage, e);
            throw new ServiceException("There was an error when finding for users for " + page + " page and with the number of users per page " + usersCountPerPage, e);

        }

    }
}
