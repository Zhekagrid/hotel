package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.OrderDao;
import com.hrydziushka.finalproject.model.dao.mapper.impl.OrderMapperImpl;
import com.hrydziushka.finalproject.model.entity.Order;
import com.hrydziushka.finalproject.model.entity.OrderStatus;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.hrydziushka.finalproject.model.dao.ColumnName.DATE_FROM;
import static com.hrydziushka.finalproject.model.dao.ColumnName.DATE_TO;

public class OrderDaoImpl implements OrderDao, BaseDao<Order> {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_ORDERS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT = """
            SELECT order_id, date_from, date_to, total_price,
            status, user_id, apartment_id
            FROM orders LIMIT ?,?""";
    private static final String SELECT_ALL_ORDERS_BY_USER_ID_IN_RANGE_BY_OFFSET_AND_ROW_COUNT = """
            SELECT order_id, date_from, date_to, total_price,
            status, user_id, apartment_id
            FROM orders  WHERE user_id=? LIMIT ?,?""";


    private static final String INSERT_ORDER = """
            INSERT INTO orders (apartment_id, user_id, date_from, date_to, total_price, status) VALUES (?,?,?,?,?,?)
            """;
    private static final String SELECT_BOOKED_DATES_BY_APARTMENT_ID = """
            SELECT date_from, date_to FROM orders WHERE apartment_id=?""";
    private static OrderDaoImpl instance = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getApartmentId());
            statement.setLong(2, order.getUserId());
            statement.setDate(3, Date.valueOf(order.getFrom()));
            statement.setDate(4, Date.valueOf(order.getTo()));
            statement.setBigDecimal(5, order.getTotalPrice());
            statement.setString(6, order.getOrderStatus().toString());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            logger.error("An error occurred when inserting a order into the database.", e);
            throw new DaoException("An error occurred when inserting a order into the database.", e);

        }
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return false;
    }

    @Override
    public List<Order> findAllInRange(int offset, int rowCount) throws DaoException {

        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT)) {
            statement.setInt(1, offset);
            statement.setInt(2, rowCount);
            try (ResultSet resultSet = statement.executeQuery()) {
                OrderMapperImpl orderMapper = OrderMapperImpl.getInstance();
                while (resultSet.next()) {

                    Order order = orderMapper.mapRow(resultSet);
                    orderList.add(order);

                }
            }
        } catch (SQLException e) {
            logger.error("An error occurred when trying to find users in the interval.", e);
            throw new DaoException("An error occurred when trying to find users in the interval.", e);
        }

        return orderList;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        return false;
    }

    @Override
    public List<ImmutablePair<LocalDate, LocalDate>> findBooksDateByApartmentId(Long apartmentId) throws DaoException {
        List<ImmutablePair<LocalDate, LocalDate>> datePairList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BOOKED_DATES_BY_APARTMENT_ID)) {
            statement.setLong(1, apartmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    LocalDate fromDate = resultSet.getTimestamp(DATE_FROM).toLocalDateTime().toLocalDate();
                    LocalDate toDate = resultSet.getTimestamp(DATE_TO).toLocalDateTime().toLocalDate();
                    ImmutablePair<LocalDate, LocalDate> localDatePair = ImmutablePair.of(fromDate, toDate);
                    datePairList.add(localDatePair);

                }
            }
        } catch (SQLException e) {
            logger.error("An error occurred when trying to find users in the interval.", e);
            throw new DaoException("An error occurred when trying to find users in the interval.", e);
        }


        return datePairList;
    }
}
