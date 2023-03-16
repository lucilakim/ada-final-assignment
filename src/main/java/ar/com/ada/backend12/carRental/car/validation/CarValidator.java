package ar.com.ada.backend12.carRental.car.validation;

import ar.com.ada.backend12.carRental.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CarValidator {
    private static final String CAR_PLATE_REGEX = "^[A-Za-z]{3}[0-9]{3}$";
    private static final Set<String> CAR_TYPES = new HashSet<>(Arrays.asList("sedan", "hatchback", "suv", "van"));
    private static final Set<String> AIR_CONDITIONING_VALUES = new HashSet<>(Arrays.asList("yes", "no"));

    public static void validateGetInput(String carPlateId) {
        validateCondition(carPlateId.matches(CAR_PLATE_REGEX), "The license plate format is wrong. " +
                "It should contain 3 contiguous letters and then 3 numbers. Ex ABC123.");
    }

    public static void validateGetAllInput(String carType, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) {
        if (carType != null) {
            carType = carType.trim().toLowerCase();
            validateCondition(!carType.equals(""), "The carType field cannot be empty or null.");
            validateCondition(CAR_TYPES.contains(carType), "The type of car is not available. The available ones are Sedan, Hatchback, SUV, or Van.");
        }
        if (passengersNumber != null) {
            validateCondition(passengersNumber > 0, "The passengers number should be greater than 0.");
        }
        if (airConditioning != null) {
            airConditioning = airConditioning.trim().toLowerCase();
            validateCondition(!airConditioning.equals(""), "The airConditioning field cannot be empty or null.");
            validateCondition(AIR_CONDITIONING_VALUES.contains(airConditioning), "The air conditioning value is not valid. The options are yes or no.");
        }
        if (dailyRent != null) {
            validateCondition(dailyRent.equals(""), "The dailyRent field cannot be null.");
            validateCondition(dailyRent.compareTo(BigDecimal.valueOf(0)) >= 0, "The daily rent should be greater than 0");
        }
    }

    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }
}

