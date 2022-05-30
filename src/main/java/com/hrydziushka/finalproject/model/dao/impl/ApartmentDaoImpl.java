package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.ApartmentDao;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.mapper.impl.ApartmentMapperImpl;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hrydziushka.finalproject.model.dao.ColumnName.APARTMENTS_COUNT;

public class ApartmentDaoImpl implements BaseDao<Apartment>, ApartmentDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_APARTMENTS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT = """
            SELECT apartment_id, people_count , class, price_per_day,
            apartment_number, description,AVG(rating) AS average_rating
            FROM apartments
            LEFT JOIN orders USING(apartment_id)
            LEFT JOIN reviews USING(order_id)
            GROUP BY apartment_id
            LIMIT ?,?
                  """;
    private static final String SELECT_APARTMENT_BY_ID = """
              SELECT apartment_id, people_count , class, price_per_day,
            apartment_number, description,AVG(rating) AS average_rating
            FROM apartments
            LEFT JOIN orders USING(apartment_id)
            LEFT JOIN reviews USING(order_id)
            WHERE apartment_id=?
            GROUP BY apartment_id
            """;
    private static final String SELECT_APARTMENTS_COUNT = "SELECT COUNT(apartment_id) AS apartments_count FROM apartments";
    private static ApartmentDaoImpl instance = new ApartmentDaoImpl();

    private ApartmentDaoImpl() {
    }

    public static ApartmentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(Apartment apartment) throws DaoException {
        return 0;
    }

    @Override
    public boolean delete(Apartment apartment) throws DaoException {
        return false;
    }


    @Override
    public boolean update(Apartment apartment) throws DaoException {
        return false;
    }


    @Override
    public int apartmentsCount() throws DaoException {
        int apartmentsCount = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_APARTMENTS_COUNT)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    apartmentsCount = resultSet.getInt(APARTMENTS_COUNT);
                }

            }
        } catch (SQLException e) {
            logger.error("There was an error in finding the number of apartments.", e);
            throw new DaoException(e);

        }
        return apartmentsCount;

    }

    @Override
    public List<Apartment> findAllInRange(int offset, int rowCount) throws DaoException {
        List<Apartment> apartmentList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_APARTMENTS_IN_RANGE_BY_OFFSET_AND_ROW_COUNT)) {
            statement.setLong(1, offset);
            statement.setLong(2, rowCount);
            try (ResultSet resultSet = statement.executeQuery()) {
                ApartmentMapperImpl apartmentMapper = ApartmentMapperImpl.getInstance();
                while (resultSet.next()) {
                    Apartment apartment = apartmentMapper.mapResultSet(resultSet);
                    apartmentList.add(apartment);
                }
            }
        } catch (SQLException e) {
            logger.error("There was an error in finding the apartments on the segment. Offset: " + offset + "RowCount: " + rowCount, e);
            throw new DaoException(e);

        }


        return apartmentList;
    }


    @Override
    public Optional<Apartment> findApartmentById(Long apartmentId) throws DaoException {
        Optional<Apartment> optionalApartment = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_APARTMENT_BY_ID)) {
            statement.setLong(1, apartmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ApartmentMapperImpl apartmentMapper = ApartmentMapperImpl.getInstance();
                    Apartment apartment = apartmentMapper.mapResultSet(resultSet);
                    optionalApartment = Optional.of(apartment);
                }
            }

        } catch (SQLException e) {
            logger.error("There was an error finding an apartment by id: "+apartmentId,e);
            throw new DaoException(e);
        }
        return optionalApartment;
    }
}
