package com.hrydziushka.finalproject.model.dao.mapper.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.mapper.Mapper;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.ApartmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class ApartmentMapperImpl implements Mapper<Apartment> {
    private static final Logger logger = LogManager.getLogger();
    private static ApartmentMapperImpl instance = new ApartmentMapperImpl();

    private ApartmentMapperImpl() {
    }

    public static ApartmentMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Apartment mapResultSet(ResultSet resultSet) throws DaoException {
        try {
            Apartment apartment = Apartment.newBuilder()
                    .setId(resultSet.getLong(APARTMENT_ID))
                    .setApartmentNumber(resultSet.getInt(APARTMENT_NUMBER))
                    .setApartmentType(ApartmentType.valueOf(resultSet.getString(APARTMENT_CLASS).toUpperCase()))
                    .setPeopleCount(resultSet.getInt(PEOPLE_COUNT))
                    .setAverageRating(resultSet.getDouble(AVERAGE_RATING))
                    .setDayPrice(resultSet.getBigDecimal(PRICE_PER_DAY))
                    .setDescription(resultSet.getString(DESCRIPTION))
                    .build();

            return apartment;
        } catch (SQLException e) {
            logger.error("An error occurred when creating an Appartment object in the mapper", e);
            throw new DaoException("An error occurred when creating an Appartment object in the mapper",e);
        }
    }
}
