package com.hrydziushka.finalproject.util.validator.impl;

import com.hrydziushka.finalproject.util.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OrderValidatorImpl implements OrderValidator {
    private static OrderValidatorImpl instance=new OrderValidatorImpl();
    private  OrderValidatorImpl(){}

    public static OrderValidatorImpl getInstance() {
        return instance;
    }

    private static final Logger logger= LogManager.getLogger();
    @Override
    public boolean validateDate(String date) {
        boolean isDateValid = false;
        if (date != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
            try {
                dateTimeFormatter.parse(date);
                isDateValid = true;
            } catch (DateTimeParseException e) {
               logger.error("Date isn't valid. Date: "+date);
            }

        }
        return isDateValid;
    }
}
