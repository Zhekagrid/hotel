package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.UserDao;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import com.hrydziushka.finalproject.util.PasswordEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    public static final String SELECT_LOGIN_PASSWORD_FROM_USERS_WHERE_LOGIN = """
            SELECT user_id, login, password, email, phone_number, balance, status, role
            FROM users JOIN  roles USING(role_id)
            JOIN statuses USING (status_id)
            WHERE login=?""";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override

    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws DaoException {

        Optional<User> optionalUser = Optional.empty();


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD_FROM_USERS_WHERE_LOGIN)) {

            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String hashPassFromDb = resultSet.getString(2);
                    PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
                   // boolean match = passwordEncryptor.checkPasswordMatching(password, hashPassFromDb);
//                    if (match) {
//                        //todo mapper
//                        //  user=
//                    }


                }
            }


        } catch (SQLException e) {
            //todo log
            throw new DaoException(e);
        }
        return optionalUser;
    }
}
