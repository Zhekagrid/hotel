package com.hrydziushka.finalproject.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

 class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_PROPERTY_PATH = "config/database.properties";
    private static final String DATABASE_DRIVER_KEY = "driver";
    private static final String DATABASE_URL_KEY = "url";
    private static final String DATABASE_USER_KEY = "user";
    private static final String DATABASE_PASSWORD_KEY = "password";
    private static final String DATABASE_DRIVER;
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    static {
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTY_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            DATABASE_DRIVER = properties.getProperty(DATABASE_DRIVER_KEY);
            DATABASE_URL = properties.getProperty(DATABASE_URL_KEY);
            DATABASE_USER = properties.getProperty(DATABASE_USER_KEY);
            DATABASE_PASSWORD = properties.getProperty(DATABASE_PASSWORD_KEY);
            Class.forName(DATABASE_DRIVER);
        } catch (IOException e) {
            logger.fatal("Can't read database properties", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Unable to register driver", e);
            throw new RuntimeException(e);
        }


    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            logger.error("Can't create connection");
        }
        return connection;
    }
}
