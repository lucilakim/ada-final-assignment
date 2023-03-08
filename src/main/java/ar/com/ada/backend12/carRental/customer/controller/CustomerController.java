package ar.com.ada.backend12.carRental.customer.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;
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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

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
            @RequestParam(name = "firstName") String firstName
            ,@RequestParam(name = "lastName") String lastName
            ,@RequestParam(name = "birthDate") String stringBirthDate
            ,@RequestParam(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "idCardExpiration") String stringIdCardExpiration
            ,@RequestParam(name = "phoneNumber") String phoneNumber
    ){
        logger.info("Trying to insert a customer");
        logger.debug("customerIdCardNumber ["+ idCardNumber +"]");

        Date birthDate = null;
        Date idCardExpiration = null;
        if(dateValidator.isValid(stringBirthDate) || dateValidator.isValid(stringIdCardExpiration)){
            try {
                birthDate = dateUtil.parseDate(stringBirthDate);
                idCardExpiration = dateUtil.parseDate(stringIdCardExpiration);
            } catch (Exception e) {
                logger.error("Error trying to change data type from string to date", e);
                return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Customer could not be inserted."), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. The expected format is yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(firstName, lastName, birthDate, idCardNumber, idCardExpiration, phoneNumber);
        logger.debug(customer.toString());
        try {
            Customer customerSaved = customerService.save(customer);
            return new ResponseEntity<ApiReturnable>(customerSaved,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error has occurred trying to enter a user");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the client could not be entered. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> update(
            @PathVariable(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "firstName", required = false) String firstName
            ,@RequestParam(name = "lastName", required = false) String lastName
            ,@RequestParam(name = "birthDate", required = false) String stringBirthDate
            ,@RequestParam(name = "idCardExpiration", required = false) String stringIdCardExpiration
            ,@RequestParam(name = "phoneNumber", required = false) String phoneNumber
    ){
        Date birthDate = null;
        Date idCardExpiration = null;
        if(stringBirthDate != null) {
            if(dateValidator.isValid(stringBirthDate)){
                try {
                    birthDate = dateUtil.parseDate(stringBirthDate);
                } catch (Exception e) {
                    logger.error("Error trying to change the data type from string to date in birthDate", e);
                    return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Customer could not be updated."), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. " + stringBirthDate + " The expected format is yyyy-MM-dd in birthDate"), HttpStatus.BAD_REQUEST);
            }
        }
        if(stringIdCardExpiration != null) {
            if(dateValidator.isValid(stringIdCardExpiration)){
                try {
                    idCardExpiration = dateUtil.parseDate(stringIdCardExpiration);
                } catch (Exception e) {
                    logger.error("Error trying to change the data type from string to date in birthDate", e);
                    return new ResponseEntity<ApiReturnable>(new ApiMessage("An error has occurred and the Customer could not be updated."), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The date format is not valid. " + stringIdCardExpiration + " The expected format is yyyy-MM-dd in IdCardExpiration"), HttpStatus.BAD_REQUEST);
            }
        }
        Customer customer = new Customer(firstName, lastName, birthDate, idCardNumber, idCardExpiration, phoneNumber);

        try {
            Customer updatedCustomer = customerService.update(customer);
            if(updatedCustomer != null) {
                return new ResponseEntity<ApiReturnable>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The client with ID number " + idCardNumber + " was not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            String errorMessage = "An error has occurred trying to enter a user with id: " + idCardNumber;
            logger.error(errorMessage);
            return new ResponseEntity<ApiReturnable>(new ApiMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "idCardNumber") Integer idCardNumber) {
        logger.info("Trying to get a customer");
        logger.debug("idCardNumber ["+ idCardNumber +"]");

        try {
            Optional<Customer> optionalCustomer = customerService.get(idCardNumber);
            if(optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                return new ResponseEntity<ApiReturnable>(customer, HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("Customer not found."), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred trying to get a customer by idCardNumber: " + idCardNumber);
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An error occurred trying to get a customer by idCardNumber: " + idCardNumber + ". Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer")
    private ResponseEntity<ApiReturnable> getAll() {
        logger.info("Trying to get all the customers");
        try {
            CustomerList customerList = customerService.getAll();
            if(!customerList.getCustomerList().isEmpty()){
                return new ResponseEntity<ApiReturnable>(customerList, HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The list of clients is empty. There is no client yet."), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred trying to get all the customers.");
            return new ResponseEntity<ApiReturnable>(new ApiMessage("An error occurred trying to get all the Customers. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> delete(
            @PathVariable(name = "idCardNumber") Integer idCardNumber
    ) {
        logger.info("Trying to delete a customer");
        logger.debug("idCardNumber ["+ idCardNumber +"]");

        try {
            Boolean isDeleted = customerService.delete(idCardNumber);
            if(isDeleted) {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The client with identity card Number: " + idCardNumber + " was successfully deleted."), HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiReturnable>(new ApiMessage("The client with identity card Number: " + idCardNumber + " was not found."), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            String errorDeleteMessage = "An error has occurred, and the customer with idCardNumber: " + idCardNumber + " could not be deleted.";
            logger.error(errorDeleteMessage);
            return new ResponseEntity<ApiReturnable>(new ApiMessage(errorDeleteMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
