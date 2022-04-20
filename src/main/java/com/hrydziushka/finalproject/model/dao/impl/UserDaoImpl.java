package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.UserDao;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    public static final String SELECT_LOGIN_PASSWORD_FROM_USERS_WHERE_LOGIN = "SELECT login, password FROM users WHERE login=?";
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
    public boolean authenticate(String login, String password) throws DaoException {
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        } catch (SQLException e) {
//          throw new DaoException(e);
//        }
//        String url = "com.mysql.cj.jdbc.Driver";
//        Properties prop = new Properties();
//        prop.put("user", "root");
//        prop.put("password", "Root_123");
//
//        boolean match = false;
//        try (Connection connection = DriverManager.getConnection(url, prop);
//             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD_FROM_PHONEBOOK_WHERE_LOGIN)) {
//            String passFromDb;
//
//            statement.setString(1, login);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                passFromDb = resultSet.getString(2);
//                System.out.println(passFromDb);
//                match = password.equals(passFromDb);
//            }
//
//        } catch (SQLException e) {
//
//            throw new DaoException("sql authenticate failed",e);
//        }


        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD_FROM_USERS_WHERE_LOGIN);) {


            String passFromDb;

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passFromDb = resultSet.getString(2);

                match = password.equals(passFromDb);

            }

        } catch (SQLException e) {

            throw new DaoException(e);
        }
        return match;
    }
}
