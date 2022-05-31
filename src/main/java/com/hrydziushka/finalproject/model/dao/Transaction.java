package com.hrydziushka.finalproject.model.dao;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public void startTransaction(BaseDao... baseDaos) throws DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("There was an error at the start of the transaction", e);
            throw new DaoException("There was an error at the start of the transaction", e);
        }
        for (BaseDao dao : baseDaos) {
            dao.setConnection(connection);

        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("There was an error at the doing transaction rollback", e);
            throw new DaoException("There was an error at the doing transaction rollback", e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("There was an error at the doing transaction commit", e);
            throw new DaoException("There was an error at the doing transaction commit", e);
        }
    }


    @Override
    public void close() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("There was an error at the connection closing", e);
                throw new DaoException("There was an error at the connection closing", e);
            }
        }
        connection = null;
    }
}
