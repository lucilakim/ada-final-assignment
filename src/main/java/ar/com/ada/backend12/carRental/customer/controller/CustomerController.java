package ar.com.ada.backend12.carRental.customer.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.contract.dto.PatchCustomerBody;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;
import ar.com.ada.backend12.carRental.customer.service.CustomerService;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
import jakarta.transaction.Transactional;
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
            @RequestParam(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "firstName") String firstName
            ,@RequestParam(name = "lastName") String lastName
            ,@RequestParam(name = "birthDate") String stringBirthDate
            ,@RequestParam(name = "idCardExpiration") String stringIdCardExpiration
            ,@RequestParam(name = "phoneNumber") String phoneNumber
    ){
        logger.info("Trying to insert a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));

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

        Customer customer = new Customer(idCardNumber, firstName, lastName, birthDate, idCardExpiration, phoneNumber);
        Customer customerSaved = customerService.save(customer);
        return new ResponseEntity<ApiReturnable>(customerSaved,HttpStatus.OK);
    }

    @PatchMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> update(
            @PathVariable(name = "idCardNumber") Integer idCardNumber,
            @RequestBody PatchCustomerBody customerBody
    ){
        logger.info("Trying to update a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));
        String stringBirthDate = customerBody.getBirthDate();
        String stringIdCardExpiration  = customerBody.getIdCardExpiration();
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

        Customer customer = new Customer(idCardNumber, customerBody.getFirstName(), customerBody.getLastName(), birthDate, idCardExpiration, customerBody.getPhoneNumber());
        Customer updatedCustomer = customerService.update(customer);
        return new ResponseEntity<ApiReturnable>(updatedCustomer, HttpStatus.OK);

    }

    @GetMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> get(
            @PathVariable(name = "idCardNumber") Integer idCardNumber
    ){
        logger.info("Trying to get a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));

        Optional<Customer> customer = customerService.get(idCardNumber);
        return new ResponseEntity<ApiReturnable>(customer.get(), HttpStatus.OK);
    }

    @GetMapping("/customer")
    private ResponseEntity<ApiReturnable> getAll() {
        logger.info("Trying to get all the customers");
        CustomerList customerList = customerService.getAll();
        return new ResponseEntity<ApiReturnable>(customerList, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> delete(
            @PathVariable(name = "idCardNumber") Integer idCardNumber
    ) {
        logger.info("Trying to delete a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));

        customerService.delete(idCardNumber);
        return new ResponseEntity<ApiReturnable>(new ApiMessage("The client with identity card Number: " + idCardNumber + " was successfully deleted."), HttpStatus.OK);
    }
}
