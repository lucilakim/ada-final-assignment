package ar.com.ada.backend12.carRental.customer.validation;

import ar.com.ada.backend12.carRental.car.util.CustomerUtil;
import ar.com.ada.backend12.carRental.car.util.CustomerUtilImpl;
import ar.com.ada.backend12.carRental.exception.BadRequestException;

import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.DateUtilImp;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CustomerValidation {
    static Logger logger = LoggerFactory.getLogger(CustomerValidation.class);
    static DateUtil DATE_UTIL = new DateUtilImp();
    static DateValidator DATE_VALIDATOR = new DateValidatorImpl(DATE_UTIL);
    static CustomerUtil CUSTOMER_UTIL = new CustomerUtilImpl();
    private static final String PHONE_NUMBER_REGEX = "^\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}$";


    public static void validateAllInputs(Integer idCardNumber, String firstName, String lastName, String stringBirthDate,
                                         String stringIdCardExpiration, String phoneNumber) {
        validateIdCardNumber(idCardNumber);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateBirthDate(stringBirthDate);
        validateIdCardExpiration(stringIdCardExpiration);
        validatePhoneNumber(phoneNumber);
    }

    private static void validateIdCardNumber(Integer idCardNumber) {
        if (idCardNumber != null) {
            validateCondition(idCardNumber > 100000000 && idCardNumber < 1000000000,
                    "The format of the id card number is incorrect. It should contain 9 numbers.");
        }
    }

    private static void validateFirstName(String firstName) {
        if (firstName != null || !firstName.isEmpty()) {
            firstName = firstName.trim().toLowerCase();
            validateCondition(firstName == null || !firstName.equals(""),
                    "The firstName field cannot be empty or null.");
        } else {
            throw new BadRequestException("The firstName field cannot be empty or null.");
        }
    }

    private static void validateLastName(String lastName) {
        if (lastName != null || !lastName.isEmpty()) {
            lastName = lastName.trim().toLowerCase();
            validateCondition(lastName == null || !lastName.equals(""),
                    "The lastName field cannot be empty or null.");
        } else {
            throw new BadRequestException("The lastName field cannot be empty or null.");
        }
    }

    private static void validateBirthDate(String stringBirthDate) {
        if (stringBirthDate != null || !stringBirthDate.isEmpty()) {
            stringBirthDate = stringBirthDate.trim().toLowerCase();
            validateCondition(stringBirthDate == null || !stringBirthDate.equals(""),
                    "The birth date field cannot be empty or null.");
            validateCondition(DATE_VALIDATOR.isValid(stringBirthDate),
                    String.format("The birth date format %s is not valid. The expected format is yyyy-MM-dd in birthDate. " +
                            "Max values accepted for MM = 12 and for dd = 28/31.", stringBirthDate));
            final int LEGAL_AGE = 18;
            Date birthDate = null;
            try {
                birthDate = DATE_UTIL.parseDate(stringBirthDate);
            } catch (Exception e) {
                logger.error("Error trying to change the data type from string to date in birthDate", e);
                throw new RuntimeException();
            }
            validateCondition(CUSTOMER_UTIL.getAge(birthDate) > LEGAL_AGE,
                    "The customer to be registered is minor. Be must be older than 18 years to be admitted.");
        } else {
            throw new BadRequestException("The birth date field cannot be empty or null.");
        }
    }

    private static void validateIdCardExpiration(String stringIdCardExpiration) {
        if (stringIdCardExpiration != null || !stringIdCardExpiration.isEmpty()) {
            stringIdCardExpiration = stringIdCardExpiration.trim().toLowerCase();
            validateCondition(stringIdCardExpiration == null || !stringIdCardExpiration.equals(""),
                    "The id card expiration field cannot be empty or null.");
            validateCondition(DATE_VALIDATOR.isValid(stringIdCardExpiration),
                    String.format("The id card expiration format %s is not valid. The expected format is yyyy-MM-dd. " +
                            "Max values accepted for MM = 12 and for dd = 28/31.", stringIdCardExpiration));
            Date idCardExpirationDate = null;
            Date currentDate = new Date();
            try {
                idCardExpirationDate = DATE_UTIL.parseDate(stringIdCardExpiration);
            } catch (Exception e) {
                logger.error("Error trying to change the data type from string to date in birthDate", e);
                throw new RuntimeException();
            }
            validateCondition(currentDate.before(idCardExpirationDate),
                    "The customer's ID has expired. You must have a valid id card to be registered.");
        } else {
            throw new BadRequestException("The id card expiration field cannot be empty or null.");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null || !phoneNumber.isEmpty()) {
            phoneNumber = phoneNumber.trim().toLowerCase();
            validateCondition(phoneNumber == null || !phoneNumber.equals(""),
                    "The phoneNumber field cannot be empty or null.");
            validateCondition(phoneNumber.matches(PHONE_NUMBER_REGEX),
                    String.format("The phone number format %s is wrong. " +
                            "The correct format is: (NNN) NNN-NN-NN. Ex (605) 456-10-90.", phoneNumber));
        } else {
            throw new BadRequestException("The phoneNumber field cannot be empty or null.");
        }
    }

    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }
}
