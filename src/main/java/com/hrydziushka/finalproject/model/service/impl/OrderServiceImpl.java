package com.hrydziushka.finalproject.model.service.impl;

import com.hrydziushka.finalproject.exception.DaoException;
import com.hrydziushka.finalproject.exception.ServiceException;
import com.hrydziushka.finalproject.model.dao.impl.OrderDaoImpl;
import com.hrydziushka.finalproject.model.dao.impl.UserDaoImpl;
import com.hrydziushka.finalproject.model.entity.Apartment;
import com.hrydziushka.finalproject.model.entity.Order;
import com.hrydziushka.finalproject.model.entity.OrderStatus;
import com.hrydziushka.finalproject.model.entity.User;
import com.hrydziushka.finalproject.model.service.OrderService;
import com.hrydziushka.finalproject.util.validator.impl.OrderValidatorImpl;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATE_SEPARATOR = "/";


    private static OrderServiceImpl instance = new OrderServiceImpl();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean bookApartment(String fromDateStr, String toDateStr, User user, Apartment apartment) throws ServiceException {
        logger.debug("Try to book apartment " + fromDateStr + " " + toDateStr);
        OrderValidatorImpl orderValidator = OrderValidatorImpl.getInstance();
        //todo add trasaction
        if (orderValidator.validateDate(fromDateStr) && orderValidator.validateDate(toDateStr)) {
            try {
                OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
                List<ImmutablePair<LocalDate, LocalDate>> bookedDates = orderDao.findBooksDateByApartmentId(apartment.getId());
                LocalDate fromDate = parseDate(fromDateStr);
                LocalDate toDate = parseDate(toDateStr);
                for (ImmutablePair<LocalDate, LocalDate> bookedDateSegment : bookedDates) {
                    if (isDateInRange(fromDate, bookedDateSegment) || isDateInRange(toDate, bookedDateSegment)) {
                        return false;
                    }
                }
                //create new order
                long daysNumberInOrder = fromDate.until(toDate, ChronoUnit.DAYS);
                if (daysNumberInOrder > 0) {

                    //todo check if number degre is crashed
                    BigDecimal totalPrice = apartment.getDayPrice().multiply(new BigDecimal(daysNumberInOrder));
                    BigDecimal currentUserBalance = user.getBalance();
                    if (currentUserBalance.compareTo(totalPrice) >= 0) {
                        BigDecimal newUserBalance = currentUserBalance.subtract(totalPrice);
                        user.setBalance(newUserBalance);
                        UserDaoImpl userDao = UserDaoImpl.getInstance();
                        boolean isBalanceUpdated = userDao.updateUserBalanceById(newUserBalance, user.getId());
                        if (isBalanceUpdated) {
                            Order order = Order.newBuilder()
                                    .setApartmentId(apartment.getId())
                                    .setUserId(user.getId())
                                    .setFrom(fromDate)
                                    .setTo(toDate)
                                    .setTotalPrice(totalPrice)
                                    .setOrderStatus(OrderStatus.PENDING)
                                    .build();
                            orderDao.insert(order);
                            return true;
                        }
                    }

                }


            } catch (DaoException e) {
                //todo
                throw new ServiceException(e);
            }


        }


        return false;
    }

    @Override
    public LocalDate[] findBookDatesByApartmentId(Long apartmentId) throws ServiceException {
        List<LocalDate> allBookedDates = new ArrayList<>();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        try {
            List<ImmutablePair<LocalDate, LocalDate>> bookedDatePairList = orderDao.findBooksDateByApartmentId(apartmentId);
            for (ImmutablePair<LocalDate, LocalDate> datePair : bookedDatePairList) {
                LocalDate fromDate = datePair.getLeft();
                LocalDate toDate = datePair.getRight();
                while (!fromDate.equals(toDate)) {
                    allBookedDates.add(fromDate);
                    fromDate = fromDate.plusDays(1);
                }

                allBookedDates.add(toDate);
            }
        } catch (DaoException e) {
            //todo log
            throw new ServiceException(e);
        }
        return allBookedDates.toArray(new LocalDate[0]);
    }

    private LocalDate parseDate(String data) {
        String[] dateArray = data.split(DATE_SEPARATOR);
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        return LocalDate.of(year, month, day);
    }

    private boolean isDateInRange(LocalDate date, ImmutablePair<LocalDate, LocalDate> dateRange) {

        return date.compareTo(dateRange.getLeft()) >= 0 && date.compareTo(dateRange.getRight()) <= 0;
    }
}
