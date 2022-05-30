package com.hrydziushka.finalproject.model.dao.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.model.dao.BaseDao;
import com.hrydziushka.finalproject.model.dao.ImageDao;
import com.hrydziushka.finalproject.model.entity.ApartmentImage;
import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import static com.hrydziushka.finalproject.model.dao.ColumnName.*;

public class ImageDaoImpl implements BaseDao<ApartmentImage>, ImageDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String PICTURE_HEADER = "data:image/jpg;base64,";

    private static final String SELECT_ALL_APARTMENT_IMAGES_BY_APARTMENT_ID = """
            SELECT image_id, image
            FROM apartments
            JOIN image_apartment_links USING(apartment_id)
            JOIN images USING(image_id)
            WHERE apartment_id=?
                 """;

    private static final String SELECT_ALL_IMAGES_OF_ALL_APARTMENTS = """
             SELECT apartment_id, image_id, image
            FROM apartments
            JOIN image_apartment_links USING(apartment_id)
            JOIN images USING(image_id)
            """;
    private static ImageDaoImpl instance = new ImageDaoImpl();

    private ImageDaoImpl() {
    }

    public static ImageDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(ApartmentImage apartmentImage) throws DaoException {
        return 0;
    }

    @Override
    public boolean delete(ApartmentImage apartmentImage) throws DaoException {
        return false;
    }

    @Override
    public List<ApartmentImage> findAllInRange(int offset, int rowCount) throws DaoException {
        return null;
    }

    @Override
    public boolean update(ApartmentImage apartmentImage) throws DaoException {
        return false;
    }

    @Override
    public Set<ApartmentImage> findAllApartmentImagesByApartmentId(Long apartmentId) throws DaoException {
        Set<ApartmentImage> apartmentImages = new HashSet<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_APARTMENT_IMAGES_BY_APARTMENT_ID)) {
            statement.setLong(1, apartmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blob image = resultSet.getBlob(IMAGE);
                    Long imageId = resultSet.getLong(IMAGE_ID);
                    StringBuilder encodedImageStringBuilder = new StringBuilder(PICTURE_HEADER);
                    InputStream pictureStream = image.getBinaryStream();
                    byte[] encodeImage = Base64.encodeBase64(pictureStream.readAllBytes(), false);
                    String imageStr = StringUtils.newStringUtf8(encodeImage);
                    encodedImageStringBuilder.append(imageStr);
                    apartmentImages.add(new ApartmentImage(imageId, encodedImageStringBuilder.toString()));
                }
            }


        } catch (SQLException | IOException e) {
            logger.error("There was an error finding all the pictures of the apartment by the id of the apartment Apartment_id: " + apartmentId, e);
            throw new DaoException(e);
        }

        return apartmentImages;
    }

    @Override
    public Map<Long, Set<ApartmentImage>> findAllImagesOfAllApartments() throws DaoException {
        Map<Long, Set<ApartmentImage>> imagesOfApartments = new HashMap<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_IMAGES_OF_ALL_APARTMENTS)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blob image = resultSet.getBlob(IMAGE);
                    Long imageId = resultSet.getLong(IMAGE_ID);
                    Long apartmentId = resultSet.getLong(APARTMENT_ID);
                    StringBuilder encodedImageStringBuilder = new StringBuilder(PICTURE_HEADER);
                    InputStream pictureStream = image.getBinaryStream();
                    byte[] encodeImage = Base64.encodeBase64(pictureStream.readAllBytes(), false);
                    String imageStr = StringUtils.newStringUtf8(encodeImage);
                    encodedImageStringBuilder.append(imageStr);
                    ApartmentImage apartmentImage = new ApartmentImage(imageId, encodedImageStringBuilder.toString());
                    Set<ApartmentImage> apartmentImageSet = imagesOfApartments.get(apartmentId);
                    if (apartmentImageSet != null) {
                        apartmentImageSet.add(apartmentImage);
                    } else {
                        apartmentImageSet = new HashSet<>();
                        apartmentImageSet.add(apartmentImage);
                        imagesOfApartments.put(apartmentId, apartmentImageSet);
                    }
                }
            }


        } catch (SQLException | IOException e) {
            logger.error("There was an error when finding all the images of all the apartments",e);
            throw new DaoException(e);

        }
        return imagesOfApartments;
    }

}
