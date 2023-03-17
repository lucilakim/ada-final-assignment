package ar.com.ada.backend12.carRental.car.util;

import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class CustomerUtilImpl implements CustomerUtil {
    private static final Logger logger = LoggerFactory.getLogger(CustomerUtilImpl.class);
    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private DateUtil dateUtil;

    public CustomerUtilImpl() {
    }

    public CustomerUtilImpl(DateValidator dateValidator, DateUtil dateUtil) {
        this.dateValidator = dateValidator;
        this.dateUtil = dateUtil;
    }

    @Override
    public Date parseDate(String stringDate) {
        try {
            return dateUtil.parseDate(stringDate);
        } catch (Exception e) {
            logger.error("Error trying to change the data type from string to date in birthDate", e);
            throw new RuntimeException("An error has occurred and the Customer could not be updated.");
        }
    }

    @Override
    public int getAge(Date birthDate) {
        Date currentDate = new Date();
        OffsetDateTime startOdt = birthDate.toInstant().atOffset(ZoneOffset.UTC);
        OffsetDateTime endOdt = currentDate.toInstant().atOffset(ZoneOffset.UTC);
        return Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getYears();
    }


}
