package ar.com.ada.backend12.carRental.contract.validation;

import ar.com.ada.backend12.carRental.car.util.CustomerUtil;
import ar.com.ada.backend12.carRental.car.util.CustomerUtilImpl;
import ar.com.ada.backend12.carRental.car.validation.CarValidator;
import ar.com.ada.backend12.carRental.customer.validation.CustomerValidator;
import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.DateUtilImp;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

public class ContractValidator {
    static Logger logger = LoggerFactory.getLogger(CustomerValidator.class);
    static DateUtil DATE_UTIL = new DateUtilImp();
    static DateValidator DATE_VALIDATOR = new DateValidatorImpl(DATE_UTIL);
    static CustomerUtil CUSTOMER_UTIL = new CustomerUtilImpl(DATE_VALIDATOR, DATE_UTIL);

    public static void validateSaveInputs(String carPlateId, Integer idCardNumber, String stringStartDay, Integer duration, BigDecimal amountPaid, BigDecimal dailyRent) {
        CarValidator.validateCarPlateId(carPlateId);
        CustomerValidator.validateIdCardNumber(idCardNumber);
        validateStringStartDay(stringStartDay);
        validateDuration(duration);
        validateAmountPaid(amountPaid, duration, dailyRent);
    }

    private static void validateStringStartDay(String stringStartDay) {
        if (stringStartDay != null) {
            validateStringStartDayNotRequired(stringStartDay);
        } else {
            throw new BadRequestException("The start day field cannot be null.");
        }
    }

    private static void validateStringStartDayNotRequired(String stringStartDay) {
        if (stringStartDay != null) {
            stringStartDay = stringStartDay.trim().toLowerCase();
            validateCondition(!stringStartDay.equals(""),
                    "The start day field cannot be empty.");

            validateCondition(DATE_VALIDATOR.isValid(stringStartDay),
                    String.format("The start day format %s is not valid. The expected format is yyyy-MM-dd. " +
                            "Max values accepted for MM = 12 and for dd = 28/31.", stringStartDay));
        }
    }

    private static void validateDuration(Integer duration) {
        final int MINIMUM_DURATION = 1;
        if (duration != null) {
            validateCondition(duration > MINIMUM_DURATION,
                    "The duration is incorrect. It must be greater than 1.");
        }
    }

    private static void validateAmountPaid(BigDecimal amountPaid, Integer duration, BigDecimal dailyRent) {
        if (amountPaid != null) {
            final float MINIMUM_PERCENTAGE = 0.75f;
            BigDecimal minimumPaid = (dailyRent.multiply(BigDecimal.valueOf(duration))).multiply(BigDecimal.valueOf(MINIMUM_PERCENTAGE));
            BigDecimal balance = dailyRent.multiply(BigDecimal.valueOf(duration));
            validateCondition(amountPaid.compareTo(minimumPaid) >= 0,
                    String.format("The amount paid: %s must be greater than or equal to 75% of the contract balance: %s.", amountPaid, balance));
        }
    }

    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }
}
