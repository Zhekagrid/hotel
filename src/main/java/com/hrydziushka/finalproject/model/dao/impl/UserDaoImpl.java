package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.UserDao;
import com.hrydziushka.finalproject.model.dao.mapper.impl.UserMapperImpl;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import com.hrydziushka.finalproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_USER_BY_LOGIN = """
            SELECT user_id, login, password, email
            , phone_number, balance, status, role
            FROM users JOIN  roles USING(role_id)
            JOIN statuses USING (status_id)
            WHERE login=?""";
    private static final String SELECT_USER_BY_ID = """
            SELECT user_id, login, password, email
            , phone_number, balance, status, role
            FROM users JOIN  roles USING(role_id)
            JOIN statuses USING (status_id)
            WHERE user_id=?""";
    private static final String SELECT_USERS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT = """
            SELECT user_id, login, email
             , phone_number, balance, status, role
             FROM users JOIN  roles USING(role_id)
             JOIN statuses USING (status_id) LIMIT ?,?""";
    private static final String SELECT_ALL_USERS_COUNT = "SELECT COUNT(user_id) AS users_count FROM users";


    private static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE users SET password = ? WHERE user_id = ?";

    private static final String UPDATE_USER_BY_ID = """
             UPDATE users SET login=?, email=?,phone_number=?,balance=?,  
            role_id=(SELECT role_id FROM roles WHERE role=?), 
             status_id =(SELECT status_id FROM statuses WHERE status=?)
            WHERE user_id = ?
             """;
    private static final String INSERT_USER = """
            INSERT INTO users (login, email,balance, role_id, status_id) VALUES (?, ?,?, (SELECT role_id FROM roles WHERE role=?),
            (SELECT status_id FROM statuses WHERE status=?))
            """;
    private static final String IS_USER_WITH_EMAIL_EXISTS = "SELECT EXISTS(SELECT login FROM users WHERE email = ?) AS user_exists";
    private static final String IS_USER_WITH_LOGIN_EXISTS = "SELECT EXISTS(SELECT login FROM users WHERE login = ?) AS user_exists";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setBigDecimal(3, user.getBalance());
            statement.setString(4, user.getUserRole().name().toLowerCase());
            statement.setString(5, user.getUserStatus().name().toLowerCase());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                } else {
                    //todo ask
                    throw new DaoException();
                }
            }

        } catch (SQLException e) {
            logger.error("An error occurred when inserting a user into the database. User: " + user.toString(), e);
            throw new DaoException(e);

        }
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAllInRange(int offset, int rowCount) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USERS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT)) {
            statement.setInt(1, offset);
            statement.setInt(2, rowCount);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserMapperImpl userMapperImpl = UserMapperImpl.getInstance();
                    User user = userMapperImpl.mapResultSet(resultSet);
                    userList.add(user);

                }
            }
        } catch (SQLException e) {
            logger.error("An error occurred when trying to find users in the interval. Offset: " + offset + " RowCount: " + rowCount, e);
            throw new DaoException(e);
        }

        return userList;
    }

    @Override
    public boolean update(User user) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setBigDecimal(4, user.getBalance());

            statement.setString(5, user.getUserRole().name().toLowerCase());
            statement.setString(6, user.getUserStatus().name().toLowerCase());
            statement.setLong(7, user.getId());
            int rows = statement.executeUpdate();
            if (rows != 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("An error occurred when trying to update user's information. New user's information: " + user.toString(), e);
            throw new DaoException(e);

        }

        return false;
    }

    @Override
    public Optional<User> signIn(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String hashPassFromDb = resultSet.getString(PASSWORD);
                    PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
                    boolean match = passwordEncryptor.checkPasswordMatching(password, hashPassFromDb);
                    if (match) {
                        UserMapperImpl userMapperImpl = UserMapperImpl.getInstance();
                        User user = userMapperImpl.mapResultSet(resultSet);
                        optionalUser = Optional.of(user);
                    }


                }
            }


        } catch (SQLException e) {
            logger.error("An error occurred when trying to authenticate a user by login and password. Login: " + login + " Password: " + password, e);
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public int usersCount() throws DaoException {
        int usersCount = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usersCount = resultSet.getInt(USERS_COUNT);
                }
            }

        } catch (SQLException e) {
            logger.error("An error occurred while counting the number of users in the database", e);
            throw new DaoException(e);

        }

        return usersCount;
    }

    @Override
    public void updateUserPassword(String hashPassword, Long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_ID)) {
            statement.setString(1, hashPassword);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while trying to update the user password. User id: " + userId + "Hash password: " + hashPassword, e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean userWithSpecificEmailExists(String email) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_USER_WITH_EMAIL_EXISTS)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(USERS_EXISTS);
                }
            }

        } catch (SQLException e) {
            logger.error("An error occurred while checking the database for the uniqueness of email: " + email, e);
            throw new DaoException(e);

        }
        return true;
    }

    @Override
    public Optional<User> findUserById(Long userId) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserMapperImpl userMapper = UserMapperImpl.getInstance();
                    User user = userMapper.mapResultSet(resultSet);
                    optionalUser = Optional.of(user);

                }
            }

        } catch (SQLException e) {
            logger.error("An error occurred when searching for a user with id:" + userId, e);
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean userWithSpecificLoginExists(String login) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_USER_WITH_LOGIN_EXISTS)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(USERS_EXISTS);
                }
            }

        } catch (SQLException e) {
            logger.error("An error occurred while checking the database for the uniqueness of login: " + login, e);
            throw new DaoException(e);

        }
        return true;
    }
}
