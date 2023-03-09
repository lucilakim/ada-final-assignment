package ar.com.ada.backend12.carRental.validation;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.util.DateUtil;
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
            Date date = dateUtil.parseDate(this.dateFormat, dateString);
            return true;
        } catch (Exception e) {
            System.out.print("The format of the date entered is incorrect. Enter a valid format [" + dateFormat + "]: ");
        }
        return false;
    }
}
