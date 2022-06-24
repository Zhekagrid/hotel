package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.dao.impl.ApartmentDaoImpl;
import com.hrydziushka.finalproject.model.dao.impl.ImageDaoImpl;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.ApartmentImage;
import com.hrydziushka.finalproject.model.service.ApartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ApartmentServiceImpl implements ApartmentService {
    private static final Logger logger = LogManager.getLogger();
    private static ApartmentServiceImpl instance = new ApartmentServiceImpl();

    private ApartmentServiceImpl() {
    }

    public static ApartmentServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Apartment> findApartmentById(Long id) throws ServiceException {
        try {
            ApartmentDaoImpl apartmentDao = ApartmentDaoImpl.getInstance();
            ImageDaoImpl imageDao = ImageDaoImpl.getInstance();
            Set<ApartmentImage> apartmentImages = imageDao.findAllApartmentImagesByApartmentId(id);
            Optional<Apartment> apartmentOptional = apartmentDao.findApartmentById(id);
            apartmentOptional.ifPresent(apartment -> apartment.setImages(apartmentImages));
            return apartmentOptional;
        } catch (DaoException e) {
            //todo log
            throw new ServiceException(e);
        }
    }

    @Override
    public int apartmentsCount() throws ServiceException {
        ApartmentDaoImpl apartmentDao = ApartmentDaoImpl.getInstance();
        try {
            return apartmentDao.apartmentsCount();

        } catch (DaoException e) {
            logger.error("The error occurred while counting the number of apartments", e);
            throw new ServiceException("The error occurred while counting the number of apartments", e);

        }


    }

    @Override
    public List<Apartment> findAllApartmentForPage(int page, int apartmentsCountPerPage) throws ServiceException {
        List<Apartment> apartmentList;
        ApartmentDaoImpl apartmentDao = ApartmentDaoImpl.getInstance();
        ImageDaoImpl imageDao = ImageDaoImpl.getInstance();
        try {
            int apartmentsOffset = (page - 1) * apartmentsCountPerPage;

            apartmentList = apartmentDao.findAllInRange(apartmentsOffset, apartmentsCountPerPage);
            Map<Long, Set<ApartmentImage>> apartmentsImagesMap = imageDao.findAllImagesOfAllApartments();

            for (Apartment apartment : apartmentList) {
                Long apartmentId = apartment.getId();
                Set<ApartmentImage> apartmentImages = apartmentsImagesMap.get(apartmentId);
                if (apartmentImages != null) {
                    apartment.setImages(apartmentImages);
                }

            }


        } catch (DaoException e) {
            logger.error("There was an error when searching for apartments for the" + page + " page with the number of apartments on one page " + apartmentsCountPerPage, e);
            throw new ServiceException("There was an error when searching for apartments for the" + page + " page with the number of apartments on one page " + apartmentsCountPerPage, e);
        }


        return apartmentList;
    }
}
