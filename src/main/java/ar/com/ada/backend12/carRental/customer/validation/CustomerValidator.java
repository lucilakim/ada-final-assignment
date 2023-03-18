package ar.com.ada.backend12.carRental.customer.validation;

import ar.com.ada.backend12.carRental.util.api.ApiUtil;
import ar.com.ada.backend12.carRental.util.api.AppUtilImpl;
import ar.com.ada.backend12.carRental.exception.BadRequestException;

import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.DateUtilImp;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CustomerValidator {
    static Logger logger = LoggerFactory.getLogger(CustomerValidator.class);
    static DateUtil DATE_UTIL = new DateUtilImp();
    static DateValidator DATE_VALIDATOR = new DateValidatorImpl(DATE_UTIL);
    static ApiUtil CUSTOMER_UTIL = new AppUtilImpl(DATE_VALIDATOR, DATE_UTIL);
    private static final String PHONE_NUMBER_REGEX = "^\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}$";


    public static void validateSaveInputs(Integer idCardNumber, String firstName, String lastName, String stringBirthDate,
                                          String stringIdCardExpiration, String phoneNumber) {
        validateIdCardNumber(idCardNumber);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateBirthDate(stringBirthDate);
        validateIdCardExpiration(stringIdCardExpiration);
        validatePhoneNumber(phoneNumber);
    }

    public static void validateUpdateInputs(Integer idCardNumber, String firstName, String lastName, String stringBirthDate,
                                            String stringIdCardExpiration, String phoneNumber) {
        validateIdCardNumber(idCardNumber);
        validateFirstNameNotRequired(firstName);
        validateLastNameNotRequired(lastName);
        validateBirthDateNotRequired(stringBirthDate);
        validateIdCardExpirationNotRequired(stringIdCardExpiration);
        validatePhoneNumberNotRequired(phoneNumber);
    }

    public static void validateIdCardNumber(Integer idCardNumber) {
        if (idCardNumber != null) {
            validateCondition(idCardNumber > 100000000 && idCardNumber < 1000000000,
                    "The format of the id card number is incorrect. It should contain 9 numbers, and it can not begin with 0.");
        }
    }

    private static void validateFirstName(String firstName) {
        if (firstName != null) {
            validateFirstNameNotRequired(firstName);
        } else {
            throw new BadRequestException("The firstName field cannot be null.");
        }
    }

    private static void validateFirstNameNotRequired(String firstName) {
        if (firstName != null) {
            firstName = firstName.trim().toLowerCase();
            validateCondition(!firstName.equals(""),
                    "The firstName field cannot be empty.");
        }
    }

    private static void validateLastName(String lastName) {
        if (lastName != null) {
            validateFirstNameNotRequired(lastName);
        } else {
            throw new BadRequestException("The lastName field cannot null.");
        }
    }

    private static void validateLastNameNotRequired(String lastName) {
        if (lastName != null) {
            lastName = lastName.trim().toLowerCase();
            validateCondition(!lastName.equals(""),
                    "The lastName field cannot be empty.");
        }
    }

    private static void validateBirthDate(String stringBirthDate) {
        if (stringBirthDate != null) {
            validateBirthDateNotRequired(stringBirthDate);
        } else {
            throw new BadRequestException("The birth date field cannot be null.");
        }
    }

    private static void validateBirthDateNotRequired(String stringBirthDate) {
        if (stringBirthDate != null) {
            stringBirthDate = stringBirthDate.trim().toLowerCase();
            validateCondition(!stringBirthDate.equals(""),
                    "The birth date field cannot be empty.");
            validateCondition(DATE_VALIDATOR.isValid(stringBirthDate),
                    String.format("The birth date format %s is not valid. The expected format is yyyy-MM-dd in birthDate. " +
                            "Max values accepted for MM = 12 and for dd = 28/31.", stringBirthDate));

            final int LEGAL_AGE = 18;
            Date birthDate = CUSTOMER_UTIL.parseDate(stringBirthDate);
            validateCondition(CUSTOMER_UTIL.getAge(birthDate) > LEGAL_AGE,
                    "The customer to be registered is minor. Be must be older than 18 years to be admitted.");
        }
    }

    private static void validateIdCardExpiration(String stringIdCardExpiration) {
        if (stringIdCardExpiration != null) {
            validateIdCardExpirationNotRequired(stringIdCardExpiration);
        } else {
            throw new BadRequestException("The id card expiration field cannot be null.");
        }
    }

    private static void validateIdCardExpirationNotRequired(String stringIdCardExpiration) {
        if (stringIdCardExpiration != null) {
            stringIdCardExpiration = stringIdCardExpiration.trim().toLowerCase();
            validateCondition(!stringIdCardExpiration.equals(""),
                    "The id card expiration field cannot be empty.");

            validateCondition(DATE_VALIDATOR.isValid(stringIdCardExpiration),
                    String.format("The id card expiration format %s is not valid. The expected format is yyyy-MM-dd. " +
                            "Max values accepted for MM = 12 and for dd = 28/31.", stringIdCardExpiration));
            Date idCardExpirationDate = CUSTOMER_UTIL.parseDate(stringIdCardExpiration);
            Date currentDate = new Date();
            validateCondition(currentDate.before(idCardExpirationDate),
                    "The customer's ID has expired. You must have a valid id card to be registered.");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            validatePhoneNumberNotRequired(phoneNumber);
        } else {
            throw new BadRequestException("The phoneNumber field cannot be null.");
        }
    }

    private static void validatePhoneNumberNotRequired(String phoneNumber) {
        if (phoneNumber != null) {
            phoneNumber = phoneNumber.trim().toLowerCase();
            validateCondition(!phoneNumber.equals(""),
                    "The phoneNumber field cannot be empty.");
            validateCondition(phoneNumber.matches(PHONE_NUMBER_REGEX),
                    String.format("The phone number format %s is wrong. " +
                            "The correct format is: (NNN) NNN-NN-NN. Ex (605) 456-10-90.", phoneNumber));
        }
    }

    private static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestException(errorMessage);
        }
    }
}
