package ar.com.ada.backend12.carRental.customer.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.service.CustomerService;
import ar.com.ada.backend12.carRental.util.ApiMessage;
import ar.com.ada.backend12.carRental.util.ApiReturnable;
import ar.com.ada.backend12.carRental.util.DateUtil;
import ar.com.ada.backend12.carRental.validation.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private DateUtil dateUtil;

    @PostMapping("/customer")
    private ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "fistName") String firstName
            ,@RequestParam(name = "lastName") String lastName
            ,@RequestParam(name = "birthDate") String stringBirthDate
            ,@RequestParam(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "idCardExpiration") String stringIdCardExpiration
            ,@RequestParam(name = "phoneNumber") String phoneNumber
    ){
        Date birthDate = null;
        Date idCardExpiration = null;
        if(dateValidator.isValid(stringBirthDate) || dateValidator.isValid(stringBirthDate)){
            try {
                birthDate = dateUtil.parseDate(stringBirthDate);
                idCardExpiration = dateUtil.parseDate(stringIdCardExpiration);
            } catch (Exception e) {
                logger.error("Error trying to change data type from string to date");
            }
        } else {
            return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. The expected format is yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(firstName, lastName, birthDate, idCardNumber, idCardExpiration, phoneNumber);
        logger.info("Trying to insert a customer");
        logger.debug("name ["+ customer.getFirstName() +"]");
        logger.debug("last name ["+ customer.getLastName() +"]");
        try {
            Customer customerSaved = customerService.save(customer);
            return new ResponseEntity<ApiReturnable>(customerSaved,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error has occurred trying to enter a user");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the client could not be entered. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
