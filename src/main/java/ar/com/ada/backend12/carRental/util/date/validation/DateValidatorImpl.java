package ar.com.ada.backend12.carRental.util.date.validation;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateValidatorImpl implements DateValidator {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private String dateFormat;
    private DateUtil dateUtil;
    private static final String DATE_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    // Constructors
    @Autowired
    public DateValidatorImpl(DateUtil dateUtil) {
        super();
        this.dateFormat = "yyyy-MM-dd";
        this.dateUtil = dateUtil;
    }

    @Override
    public boolean isValid(String dateString) {
        try {
            if (dateString.matches(DATE_REGEX)) {
                Date date = dateUtil.parseDate(this.dateFormat, dateString);
                return true;
            }
        } catch (Exception e) {
            System.out.print("The format of the date entered is incorrect. Enter a valid format [" + dateFormat + "]: ");
        }
        return false;
    }
}
