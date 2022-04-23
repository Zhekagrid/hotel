package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.UserDao;
import com.hrydziushka.finalproject.model.dao.mapper.impl.UserMapper;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import com.hrydziushka.finalproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.hrydziushka.finalproject.model.dao.ColumnName.PASSWORD;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    private static final Logger logger = LogManager.getLogger();
    public static final String SELECT_USER_BY_LOGIN = """
            SELECT user_id, login, password, email
            , phone_number, balance, status, role
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
                        UserMapper userMapper = UserMapper.getInstance();
                        User user = userMapper.mapRow(resultSet);
                        optionalUser = Optional.of(user);
                    }


                }
            }


        } catch (SQLException e) {
            //todo log
            throw new DaoException(e);
        }
        return optionalUser;
    }
}
