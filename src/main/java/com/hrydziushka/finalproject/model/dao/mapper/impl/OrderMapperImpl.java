package com.hrydziushka.finalproject.model.dao.mapper.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.mapper.Mapper;
import com.hrydziushka.finalproject.model.entity.Order;
import com.hrydziushka.finalproject.model.entity.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class OrderMapperImpl implements Mapper<Order> {
    private static final Logger logger = LogManager.getLogger();
    private static OrderMapperImpl instance = new OrderMapperImpl();

    private OrderMapperImpl() {
    }

    public static OrderMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Order mapRow(ResultSet resultSet) throws DaoException {
        try {

            LocalDate fromDate = resultSet.getTimestamp(DATE_FROM).toLocalDateTime().toLocalDate();
            LocalDate toDate = resultSet.getTimestamp(DATE_TO).toLocalDateTime().toLocalDate();
            Order order = Order.newBuilder()
                    .setOrderId(resultSet.getLong(ORDER_ID))
                    .setApartmentId(resultSet.getLong(APARTMENT_ID))
                    .setUserId(resultSet.getLong(USER_ID))
                    .setFrom(fromDate)
                    .setTo(toDate)
                    .setTotalPrice(resultSet.getBigDecimal(TOTAL_PRICE))
                    .setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()))
                    .build();
            return order;
        } catch (SQLException e) {
            logger.error("An error occurred when creating a User object in the mapper", e);
            throw new DaoException("An error occurred when creating a User object in the mapper", e);
        }
    }
}
