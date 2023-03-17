package ar.com.ada.backend12.carRental.car.validation;

import ar.com.ada.backend12.carRental.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CarValidator {
    private static final String CAR_PLATE_REGEX = "^[A-Za-z]{3}[0-9]{3}$";
    private static final Set<String> CAR_TYPES = new HashSet<>(Arrays.asList("sedan", "hatchback", "suv", "van"));
    private static final Set<String> CAR_BRANDS = new HashSet<>(Arrays.asList("Audi", "BMW", "renault", "lexus", "ford", "bmw", "honda", "toyota"));
    private static final Set<String> AIR_CONDITIONING_VALUES = new HashSet<>(Arrays.asList("yes", "no"));

    public static void validateSaveInputs(String carPlateId, String brand, String model, String color, String carType,
                                          Integer passengersNumber, Integer mileage, String airConditioning, BigDecimal dailyRent){
        validateCarPlateId(carPlateId);
        validateBrand(brand);
        validateModel(model);
        validateColor(color);
        validateCarType(carType);
        validatePassengerNumber(passengersNumber);
        validateMileage(mileage);
        validateAirConditioning(airConditioning);
        validateDailyRent(dailyRent);
    }
    public static void validateUpdateInputs(String carPlateId, String brand, String model,String color,String carType,
                                            Integer passengersNumber, Integer mileage, String airConditioning,BigDecimal dailyRent) {
        validateCarPlateId(carPlateId);
        validateBrandNotRequired(brand);
        validateModelNotRequired(model);
        validateColorNotRequired(color);
        validateCarTypeNotRequired(carType);
        validatePassengerNumber(passengersNumber);
        validateMileage(mileage);
        validateAirConditioningNotRequired(airConditioning);
        validateDailyRent(dailyRent);
    }
    public static void validateGetAllInput(String carType, Integer passengersNumber, String airConditioning, BigDecimal dailyRent) {
        validateCarTypeNotRequired(carType);
        validatePassengerNumber(passengersNumber);
        validateAirConditioningNotRequired(airConditioning);
        validateDailyRent(dailyRent);
    }

    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }

    public static void validateCarPlateId(String carPlateId) {
        if (carPlateId != null) {
            validateCondition(!carPlateId.equals(""), "The model field cannot be empty or null.");
            validateCondition(carPlateId.matches(CAR_PLATE_REGEX), "The license plate format is wrong. " +
                    "It should contain 3 contiguous letters and then 3 numbers. Ex ABC123.");
        } else {
            throw new BadRequestException("The airConditioning field cannot be empty or null.");
        }
    }
    public static boolean carPlateIdIsValid(String carPlateId) {
       if (!carPlateId.matches(CAR_PLATE_REGEX)) {
           return false;
       }
       return true;
    }
    private static void validateModel(String model){
        validateModelNotRequired(model);
        validateCondition(model != null, "The brand field cannot be empty or null.");
    }
    private static void validateModelNotRequired(String model){
        if (model != null) {
            model = model.trim().toLowerCase();
            validateCondition(!model.equals(""), "The model field cannot be empty or null.");
        }
    }

    private static void validateColor(String color) {
        validateColorNotRequired(color);
        validateCondition(color != null, "The brand field cannot be empty or null.");
    }
    private static void validateColorNotRequired(String color) {
        if (color != null) {
            color = color.trim().toLowerCase();
            validateCondition(color == null || !color.equals(""), "The model field cannot be empty or null.");
        }
    }

    private static void validateCarType(String carType) {
        validateCarTypeNotRequired(carType);
        validateCondition(carType != null, "The carType field cannot be empty or null.");
    }
    private static void validateCarTypeNotRequired(String carType) {
        if (carType != null) {
            carType = carType.trim().toLowerCase();
            validateCondition(!carType.equals(""), "The carType field cannot be empty.");
            validateCondition(CAR_TYPES.contains(carType), "The type of car is not available. The available ones are Sedan, Hatchback, SUV, or Van.");
        }
    }

    private static void validateBrand(String brand) {
        validateBrandNotRequired(brand);
        validateCondition(brand != null, "The brand field cannot be empty or null.");
    }
    private static void validateBrandNotRequired(String brand) {
        if (brand != null) {
            brand = brand.trim().toLowerCase();
            validateCondition(brand == null || !brand.equals(""), "The brand field cannot be empty or null.");
            validateCondition(CAR_BRANDS.contains(brand), "The brand of the car is not available. The available ones are Audi, BMW, Renault, Lexus, Ford, BMW, Honda or Toyota.");
        }
    }

    private static void validatePassengerNumber(Integer passengersNumber) {
        if (passengersNumber != null) {
            validateCondition(passengersNumber > 1, "The passengers number should be greater than 0.");
        }
    }

    private static void validateMileage(Integer mileage) {
        if (mileage != null) {
            validateCondition(mileage >= 0, "The mileage should be greater than 0.");
        }
    }

    private static void validateAirConditioning(String airConditioning) {
        validateAirConditioningNotRequired(airConditioning);
        validateCondition(airConditioning != null, "The airConditioning field cannot be empty or null.");
    }
    private static void validateAirConditioningNotRequired(String airConditioning){
        if (airConditioning != null) {
            airConditioning = airConditioning.trim().toLowerCase();
            validateCondition(!airConditioning.equals(""), "The airConditioning field cannot be empty or null.");
            validateCondition(AIR_CONDITIONING_VALUES.contains(airConditioning), "The air conditioning value is not valid. The options are yes or no.");
        }
    }

    private static void validateDailyRent(BigDecimal dailyRent) {
        if (dailyRent != null) {
            validateCondition(dailyRent.compareTo(BigDecimal.valueOf(0)) >= 0, "The daily rent should be greater than 0");
        }
    }
}

