package ar.com.ada.backend12.carRental.customer.controller;

import ar.com.ada.backend12.carRental.car.controller.CarController;
import ar.com.ada.backend12.carRental.car.util.CustomerUtil;
import ar.com.ada.backend12.carRental.customer.dto.PatchCustomerReqBody;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import ar.com.ada.backend12.carRental.customer.model.CustomerList;
import ar.com.ada.backend12.carRental.customer.service.CustomerService;
import ar.com.ada.backend12.carRental.customer.validation.CustomerValidation;
import ar.com.ada.backend12.carRental.util.api.message.ApiMessage;
import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import ar.com.ada.backend12.carRental.util.date.DateUtil;
import ar.com.ada.backend12.carRental.util.date.validation.DateValidator;
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
    @Autowired
    CustomerUtil customerUtil;

    @PostMapping("/customer")
    private ResponseEntity<ApiReturnable> save(
            @RequestParam(name = "idCardNumber") Integer idCardNumber
            ,@RequestParam(name = "firstName") String firstName
            ,@RequestParam(name = "lastName") String lastName
            ,@RequestParam(name = "birthDate") String stringBirthDate
            ,@RequestParam(name = "idCardExpiration") String stringIdCardExpiration
            ,@RequestParam(name = "phoneNumber") String phoneNumber
    ){
        CustomerValidation.validateAllInputs(idCardNumber, firstName, lastName, stringBirthDate, stringIdCardExpiration, phoneNumber);
        Date birthDate = customerUtil.parseDate(stringBirthDate);
        Date idCardExpiration = customerUtil.parseDate(stringIdCardExpiration);

        logger.info("Trying to insert a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));
        Customer customer = new Customer(idCardNumber, firstName, lastName, birthDate, idCardExpiration, phoneNumber);
        Customer customerSaved = customerService.save(customer);
        return new ResponseEntity<ApiReturnable>(customerSaved,HttpStatus.OK);
    }

    @PatchMapping("/customer/{idCardNumber}")
    private ResponseEntity<ApiReturnable> update(
            @PathVariable(name = "idCardNumber") Integer idCardNumber,
            @RequestBody PatchCustomerReqBody customerBody
    ){
        CustomerValidation.validateAllInputs(idCardNumber, customerBody.getFirstName(), customerBody.getLastName(),
                customerBody.getBirthDate(), customerBody.getIdCardExpiration(), customerBody.getPhoneNumber());
        Date birthDate = customerUtil.parseDate(customerBody.getBirthDate());
        Date idCardExpiration = customerUtil.parseDate(customerBody.getIdCardExpiration());

        logger.info("Trying to update a customer");
        logger.debug(String.format("idCardNumber [ %s ]", idCardNumber));
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
    @PatchMapping("/customer/")
    private ResponseEntity<ApiReturnable> update() {
        logger.info("Trying to update a Customer in the database without id card number.");
        return new ResponseEntity<>(new ApiMessage("The id card number of the Customer can not be null or empty. " +
                "Please try again with the correct path ex /customer/123456789."), HttpStatus.BAD_REQUEST);
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
