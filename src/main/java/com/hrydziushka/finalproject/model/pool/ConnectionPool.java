package com.hrydziushka.finalproject.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 5;

    private static ConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock createLock = new ReentrantLock();


    private BlockingQueue<ProxyConnection> usedConnections;
    private BlockingQueue<ProxyConnection> freeConnections;

    private ConnectionPool() {
        usedConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = ConnectionFactory.createConnection();
            if (connection != null) {
                try {
                    freeConnections.put(new ProxyConnection(connection));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    //todo log
                }
            } else {
                logger.error("Connection is not created");
            }
        }

        //todo chek pool size and try to add connections
    }


    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                createLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);

                    logger.info("init");
                }
            } finally {
                createLock.unlock();
            }
        }

        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {


            logger.error("Can't get connection");
            Thread.currentThread().interrupt();
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxyConnection) {

            try {

                usedConnections.remove(connection);
                freeConnections.put(proxyConnection);
                logger.info("Connection released");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("can't add connection to free connections ", e);
            }

        } else {
            logger.error("Incorrect type of connection " + connection);
        }

    }

    public void destroyPool() {

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (SQLException e) {
                logger.error("can't close connection,e");
            } catch (InterruptedException e) {
                logger.error("can't close connection,e");
                Thread.currentThread().interrupt();
            }
        }
        deregisterDriver();
        logger.info("Connection pool destroyed ");
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("can't deregister driver", e);
            }
        });

        logger.info("Drivers are deregister");
    }
}
