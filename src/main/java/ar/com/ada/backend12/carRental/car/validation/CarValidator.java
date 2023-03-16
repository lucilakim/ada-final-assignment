package ar.com.ada.backend12.carRental.car.validation;

import ar.com.ada.backend12.carRental.exception.BadRequestException;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CarValidator {
    public static final String CAR_PLATE_REGEX = "^[A-Za-z]{3}[0-9]{3}$";


    public static void validateGet(String carPlateId) {
        validateCondition(carPlateId.matches(CAR_PLATE_REGEX), "The license plate format is wrong. " +
                "It should contain 3 contiguous letters and then 3 numbers. Ex ABC123.");
    }

    public static void validateGetAllInput(String carType, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) {
        validateCondition(dailyRent.compareTo(BigDecimal.valueOf(0)) >= 0, "daily rent should be greater than 0");
        validateCondition(true, "daily rent should be greater than 0");
    }
    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return s.equals(null) || s.isEmpty();
    }
}
